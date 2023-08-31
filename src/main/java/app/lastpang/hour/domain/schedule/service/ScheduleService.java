package app.lastpang.hour.domain.schedule.service;

import app.lastpang.hour.domain.schedule.domain.CommonSchedule;
import app.lastpang.hour.domain.schedule.domain.PersonalSchedule;
import app.lastpang.hour.domain.schedule.domain.repository.CommonScheduleRepository;
import app.lastpang.hour.domain.schedule.domain.repository.PersonalScheduleRepository;
import app.lastpang.hour.domain.schedule.mapper.ScheduleMapper;
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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final CommonScheduleRepository commonScheduleRepository;
    private final PersonalScheduleRepository personalScheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final KakaoMobilityHelper kakaoMobilityHelper;
    private final UserUtils userUtils;

    // 최초 스케줄 등록 (공통, 개인 모두 추가)
    public ScheduleSaveResponse saveFirstSchedule(ScheduleSaveRequest request) {

        User user = userUtils.getCurrentUser();
        CommonSchedule commonSchedule = scheduleMapper.toCommonSchedule(request);
        commonScheduleRepository.save(commonSchedule);

        PersonalSchedule personalSchedule = getPersonalSchedule(commonSchedule, user, request);
        personalScheduleRepository.save(personalSchedule);

        return scheduleMapper.toScheduleSaveResponse(personalSchedule, commonSchedule);
    }

    // 이미 존재하는 공통 스케줄에 개인 스케줄 추가
    public ScheduleSaveResponse saveExistSchedule(ScheduleSaveRequest request, Long commonScheduleId) {

        User user = userUtils.getCurrentUser();
        CommonSchedule commonSchedule = commonScheduleRepository.findById(commonScheduleId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMON_SCHEDULE));

        PersonalSchedule personalSchedule = getPersonalSchedule(commonSchedule, user, request);
        personalScheduleRepository.save(personalSchedule);

        return scheduleMapper.toScheduleSaveResponse(personalSchedule, commonSchedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleFindAllResponse> findAllSchedule() {

        User user = userUtils.getCurrentUser();
        List<PersonalSchedule> personalScheduleList = personalScheduleRepository.findAllByUser(user);

        return personalScheduleList.stream()
                .map(scheduleMapper::toScheduleFindAllResponse)
                .collect(Collectors.toList());
    }

    private PersonalSchedule getPersonalSchedule(CommonSchedule commonSchedule, User user, ScheduleSaveRequest request) {

        LocalDateTime departureTime = getDepartureTime(commonSchedule, request);
        return scheduleMapper.toPersonalSchedule(user, commonSchedule, request.getOriginName(), departureTime);
    }

    private LocalDateTime getDepartureTime(CommonSchedule commonSchedule, ScheduleSaveRequest request) {

        Long duration = kakaoMobilityHelper.getDepartureTime(request.getOrigin(), request.getDestination(), request.getArrivalTime());
        return commonSchedule.getArrivalTime().minus(duration, ChronoUnit.MINUTES);
    }
}
