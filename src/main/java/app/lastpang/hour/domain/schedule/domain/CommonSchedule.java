package app.lastpang.hour.domain.schedule.domain;

import app.lastpang.hour.domain.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonSchedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "common_schedule_id")
    private Long id;

    private String name;                    // 일정 이름
    private String memo;                    // 일정 메모
    private String destinationName;         // 도착지
    private LocalDateTime arrivalTime;      // 도착 희망 시간

    @Builder
    public CommonSchedule(
            String name,
            String memo,
            String destinationName,
            LocalDateTime arrivalTime) {
        this.name = name;
        this.memo = memo;
        this.destinationName = destinationName;
        this.arrivalTime = arrivalTime;
    }

    public static CommonSchedule createCommonSchedule(
            String name,
            String memo,
            String destinationName,
            LocalDateTime arrivalTime) {
        return CommonSchedule.builder()
                .name(name)
                .memo(memo)
                .destinationName(destinationName)
                .arrivalTime(arrivalTime)
                .build();
    }
}
