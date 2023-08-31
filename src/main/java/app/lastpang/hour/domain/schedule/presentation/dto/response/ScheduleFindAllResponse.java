package app.lastpang.hour.domain.schedule.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ScheduleFindAllResponse {

    private Long personalScheduleId;
    private Long commonScheduleId;
    private String name;
    private String originName;              // 출발지
    private LocalDateTime departureTime;    // 출발 예정 시간
    private String destinationName;         // 도착지
    private LocalDateTime arrivalTime;      // 도착 희망 시간
}
