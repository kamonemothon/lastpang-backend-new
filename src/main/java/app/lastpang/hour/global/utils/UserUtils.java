package app.lastpang.hour.global.utils;

import app.lastpang.hour.domain.user.domain.User;
import app.lastpang.hour.domain.user.domain.repository.UserRepository;
import app.lastpang.hour.global.exception.CustomException;
import app.lastpang.hour.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class UserUtils {

    private final UserRepository userRepository;

    // TODO: 추후 인증서버 구축되면 SecurityContextHolder에서 읽어온 Authentication 속 id 값 이용하기
    public User getCurrentUser() {
        return userRepository.findById(1L)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }
}
