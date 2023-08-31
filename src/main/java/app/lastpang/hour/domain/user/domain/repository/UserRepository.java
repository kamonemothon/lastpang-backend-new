package app.lastpang.hour.domain.user.domain.repository;

import app.lastpang.hour.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}