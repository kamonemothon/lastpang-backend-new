package app.lastpang.hour.domain.user.domain;

import app.lastpang.hour.domain.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Embedded
    private Profile profile;    // 이름, 이메일, 전화번호

    @Builder
    public User(Profile profile) {
        this.profile = profile;
    }

    public static User createUser(Profile profile) {
        return User.builder()
                .profile(profile)
                .build();
    }
}