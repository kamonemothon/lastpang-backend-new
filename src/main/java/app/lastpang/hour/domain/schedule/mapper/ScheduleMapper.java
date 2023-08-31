package app.lastpang.hour.domain.schedule.mapper;

import app.lastpang.hour.domain.schedule.domain.CommonSchedule;
import app.lastpang.hour.domain.schedule.domain.PersonalSchedule;
import app.lastpang.hour.domain.schedule.presentation.dto.request.ScheduleSaveRequest;
import app.lastpang.hour.domain.schedule.presentation.dto.response.ScheduleFindAllResponse;
import app.lastpang.hour.domain.schedule.presentation.dto.response.ScheduleSaveResponse;
import app.lastpang.hour.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    public CommonSchedule toCommonSchedule(ScheduleSaveRequest request) {

        LocalDateTime arrivalTime = formatDateTime(request.getArrivalTime());

        return CommonSchedule.createCommonSchedule(
                request.getName(),
                request.getMemo(),
                request.getDestinationName(),
                arrivalTime
        );
    }

    public PersonalSchedule toPersonalSchedule(User user,
                                               CommonSchedule commonSchedule,
                                               String originName,
                                               LocalDateTime departureTime) {

        return PersonalSchedule.createPersonalSchedule(
                user,
                commonSchedule,
                originName,
                departureTime
        );
    }

    public ScheduleSaveResponse toScheduleSaveResponse(
            PersonalSchedule personalSchedule,
            CommonSchedule commonSchedule) {

        return ScheduleSaveResponse.builder()
                .personalScheduleId(personalSchedule.getId())
                .commonScheduleId(commonSchedule.getId())
                .build();
    }

    public ScheduleFindAllResponse toScheduleFindAllResponse(PersonalSchedule personalSchedule) {

        CommonSchedule commonSchedule = personalSchedule.getCommonSchedule();
        return ScheduleFindAllResponse.builder()
                .personalScheduleId(personalSchedule.getId())
                .commonScheduleId(commonSchedule.getId())
                .name(commonSchedule.getName())
                .originName(personalSchedule.getOriginName())           // 출발지
                .departureTime(personalSchedule.getDepartureTime())     // 출발 예정 시간
                .destinationName(commonSchedule.getDestinationName())   // 도착지
                .arrivalTime(commonSchedule.getArrivalTime())           // 도착 희망 시간
                .build();
    }

    private LocalDateTime formatDateTime(String dateTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
