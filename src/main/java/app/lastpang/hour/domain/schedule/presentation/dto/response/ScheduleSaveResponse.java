package app.lastpang.hour.domain.schedule.presentation.dto.response;

import lombok.Getter;

@Getter
public class ScheduleSaveResponse {

    private Long personalScheduleId;
    private Long commonScheduleId;

    public ScheduleSaveResponse(Long personalScheduleId, Long commonScheduleId) {

        this.personalScheduleId = personalScheduleId;
        this.commonScheduleId = commonScheduleId;
    }
}