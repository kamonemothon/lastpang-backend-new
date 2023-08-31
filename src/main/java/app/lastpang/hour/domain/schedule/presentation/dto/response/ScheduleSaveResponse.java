package app.lastpang.hour.domain.schedule.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleSaveResponse {

    private Long personalScheduleId;
    private Long commonScheduleId;
}