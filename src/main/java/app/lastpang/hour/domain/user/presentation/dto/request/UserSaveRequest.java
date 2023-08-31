package app.lastpang.hour.domain.user.presentation.dto.request;

import app.lastpang.hour.domain.user.domain.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequest {

    private Profile profile;
}
