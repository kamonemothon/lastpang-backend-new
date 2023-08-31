package app.lastpang.hour.domain.schedule.presentation.dto.response;

import app.lastpang.hour.domain.schedule.domain.PersonalSchedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleFindAllResponse {

    private Long personalScheduleId;
    private Long commonScheduleId;
    private String name;                    // 일정 이름
    private String originName;              // 출발지 이름
    private LocalDateTime departureTime;    // 출발 예정 시간
    private String destinationName;         // 도착지 이름
    private LocalDateTime arrivalTime;      // 도착 희망 시간

    public ScheduleFindAllResponse(PersonalSchedule personalSchedule) {
        this.personalScheduleId = personalSchedule.getId();
        this.commonScheduleId = personalSchedule.getCommonSchedule().getId();
        this.name = personalSchedule.getCommonSchedule().getName();
        this.originName = personalSchedule.getOriginName();
        this.departureTime = personalSchedule.getDepartureTime();
        this.destinationName = personalSchedule.getCommonSchedule().getDestinationName();
        this.arrivalTime = personalSchedule.getCommonSchedule().getArrivalTime();
    }
}
