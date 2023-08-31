package app.lastpang.hour.domain.user.service;

import app.lastpang.hour.domain.user.domain.User;
import app.lastpang.hour.domain.user.domain.repository.UserRepository;
import app.lastpang.hour.domain.user.presentation.dto.request.UserSaveRequest;
import app.lastpang.hour.domain.user.presentation.dto.response.UserFindOneResponse;
import app.lastpang.hour.global.exception.CustomException;
import app.lastpang.hour.global.exception.ErrorCode;
import app.lastpang.hour.global.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserUtils userUtils;

    @Transactional(readOnly = true)
    public UserFindOneResponse findOneUser() {

        User user = userUtils.getCurrentUser();
        return new UserFindOneResponse(user);
    }

    public void saveUser(UserSaveRequest request) {

        User user = User.createUser(request.getProfile());
        userRepository.save(user);
    }
}
