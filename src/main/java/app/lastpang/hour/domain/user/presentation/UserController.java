package app.lastpang.hour.domain.user.presentation;

import app.lastpang.hour.domain.user.presentation.dto.request.UserSaveRequest;
import app.lastpang.hour.domain.user.presentation.dto.response.UserFindOneResponse;
import app.lastpang.hour.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 테스트용 api
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserFindOneResponse> findOneUser() {

        UserFindOneResponse response = userService.findOneUser();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("")
    public ResponseEntity<Void> saveUser(@RequestBody UserSaveRequest request) {

        userService.saveUser(request);
        return ResponseEntity.ok().build();
    }
}
