package app.lastpang.hour.domain.schedule.presentation.dto.response;

import app.lastpang.hour.domain.schedule.domain.CommonSchedule;
import lombok.Getter;

@Getter
public class ScheduleFindByCommonResponse {

    private String name;                // 일정 이름
    private String destinationName;     // 도착지 이름
    private String arrivalTime;         // 도착 희망 시간
    private String memo;                // 일정 메모

    public ScheduleFindByCommonResponse(CommonSchedule commonSchedule) {
        this.name = commonSchedule.getName();
        this.destinationName = commonSchedule.getDestinationName();
        this.arrivalTime = commonSchedule.getArrivalTime().toString();
        this.memo = commonSchedule.getMemo();
    }
}
