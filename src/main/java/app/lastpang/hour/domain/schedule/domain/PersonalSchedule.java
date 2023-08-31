package app.lastpang.hour.domain.schedule.domain;

import app.lastpang.hour.domain.common.domain.BaseTimeEntity;
import app.lastpang.hour.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalSchedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "common_schedule_id")
    private CommonSchedule commonSchedule;

    private String originName;              // 출발지
    private LocalDateTime departureTime;    // 출발 예정 시간


    @Builder
    public PersonalSchedule(
            User user,
            CommonSchedule commonSchedule,
            String originName,
            LocalDateTime departureTime) {
        this.user = user;
        this.commonSchedule = commonSchedule;
        this.originName = originName;
        this.departureTime = departureTime;
    }

    public static PersonalSchedule createPersonalSchedule(
            User user,
            CommonSchedule commonSchedule,
            String originName,
            LocalDateTime departureTime) {
        return PersonalSchedule.builder()
                .user(user)
                .commonSchedule(commonSchedule)
                .originName(originName)
                .departureTime(departureTime)
                .build();
    }
}
