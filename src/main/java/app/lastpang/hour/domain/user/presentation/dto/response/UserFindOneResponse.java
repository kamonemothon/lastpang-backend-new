package app.lastpang.hour.domain.user.presentation.dto.response;

import app.lastpang.hour.domain.user.domain.User;
import lombok.Getter;

@Getter
public class UserFindOneResponse {

    private Long id;
    private String name;

    public UserFindOneResponse(User user) {
        id = user.getId();
        name = user.getProfile().getName();
    }
}