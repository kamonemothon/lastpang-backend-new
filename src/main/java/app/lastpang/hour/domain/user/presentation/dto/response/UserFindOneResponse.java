package app.lastpang.hour.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserFindOneResponse {

    private Long id;
    private String name;
}