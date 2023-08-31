package app.lastpang.hour.domain.schedule.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleSaveRequest {

    private String name;                // 일정 이름
    private String arrivalTime;         // 도착 희망 시간
    private String originName;          // 출발지 이름
    private String origin;              // 출발지 좌표
    private String destinationName;     // 도착지 이름
    private String destination;         // 도착지 좌표
    private String memo;                // 일정 메모
}
