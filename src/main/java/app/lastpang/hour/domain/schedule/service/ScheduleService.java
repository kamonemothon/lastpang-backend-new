package app.lastpang.hour.domain.schedule.service;

import app.lastpang.hour.domain.schedule.domain.CommonSchedule;
import app.lastpang.hour.domain.schedule.domain.PersonalSchedule;
import app.lastpang.hour.domain.schedule.domain.repository.CommonScheduleRepository;
import app.lastpang.hour.domain.schedule.domain.repository.PersonalScheduleRepository;
import app.lastpang.hour.domain.schedule.presentation.dto.request.ScheduleSaveRequest;
import app.lastpang.hour.domain.schedule.presentation.dto.response.ScheduleSaveResponse;
import app.lastpang.hour.domain.schedule.presentation.dto.response.ScheduleFindAllResponse;
import app.lastpang.hour.domain.schedule.service.helper.KakaoMobilityHelper;
import app.lastpang.hour.domain.user.domain.User;
import app.lastpang.hour.global.exception.CustomException;
import app.lastpang.hour.global.exception.ErrorCode;
import app.lastpang.hour.global.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final CommonScheduleRepository commonScheduleRepository;
    private final PersonalScheduleRepository personalScheduleRepository;
    private final KakaoMobilityHelper kakaoMobilityHelper;
    private final UserUtils userUtils;

    // 최초 스케줄 등록 (공통, 개인 모두 추가)
    public ScheduleSaveResponse saveFirstSchedule(ScheduleSaveRequest request) {

        User user = userUtils.getCurrentUser();
        LocalDateTime arrivalTime = formatDateTime(request.getArrivalTime());
        
        CommonSchedule commonSchedule = CommonSchedule.createCommonSchedule(
                request.getName(),
                request.getMemo(),
                request.getDestinationName(),
                arrivalTime
        );
        commonScheduleRepository.save(commonSchedule);

        Long duration = kakaoMobilityHelper.getDepartureTime(request.getOrigin(), request.getDestination(), convertToCustomFormat(request.getArrivalTime()));
        LocalDateTime departureTime = arrivalTime.minus(duration, ChronoUnit.MINUTES);
        PersonalSchedule personalSchedule = PersonalSchedule.createPersonalSchedule(
                user,
                commonSchedule,
                request.getOriginName(),
                departureTime
        );
        personalScheduleRepository.save(personalSchedule);

        return new ScheduleSaveResponse(personalSchedule.getId(), commonSchedule.getId());
    }

    // 이미 존재하는 공통 스케줄에 개인 스케줄 추가
    public ScheduleSaveResponse saveAddedSchedule(ScheduleSaveRequest request, Long commonScheduleId) {

        User user = userUtils.getCurrentUser();
        LocalDateTime arrivalTime = formatDateTime(request.getArrivalTime());

        CommonSchedule commonSchedule = commonScheduleRepository.findById(commonScheduleId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMON_SCHEDULE));

        Long duration = kakaoMobilityHelper.getDepartureTime(request.getOrigin(), request.getDestination(), convertToCustomFormat(request.getArrivalTime()));
        LocalDateTime departureTime = arrivalTime.minus(duration, ChronoUnit.MINUTES);

        PersonalSchedule personalSchedule = PersonalSchedule.createPersonalSchedule(
                user,
                commonSchedule,
                request.getOriginName(),
                departureTime
        );
        personalScheduleRepository.save(personalSchedule);

        return new ScheduleSaveResponse(personalSchedule.getId(), commonSchedule.getId());
    }

    private LocalDateTime formatDateTime(String dateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }

    private String convertToCustomFormat(String dateTime) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = inputFormat.parse(dateTime);
            return outputFormat.format(date);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public List<ScheduleFindAllResponse> findAllSchedule() {

        User user = userUtils.getCurrentUser();
        List<PersonalSchedule> personalScheduleList = personalScheduleRepository.findAllByUser(user);

        return personalScheduleList.stream()
                .map(ScheduleFindAllResponse::new)
                .collect(Collectors.toList());
    }
}
