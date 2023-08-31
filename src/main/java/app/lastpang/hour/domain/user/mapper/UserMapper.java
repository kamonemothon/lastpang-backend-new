package app.lastpang.hour.domain.user.mapper;

import app.lastpang.hour.domain.user.domain.User;
import app.lastpang.hour.domain.user.presentation.dto.request.UserSaveRequest;
import app.lastpang.hour.domain.user.presentation.dto.response.UserFindOneResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User toUser(UserSaveRequest request) {
        return User.createUser(request.getProfile());
    }

    public UserFindOneResponse toUserFindOneResponse(User user) {

        return UserFindOneResponse.builder()
                .id(user.getId())
                .name(user.getProfile().getName())
                .build();
    }
}
