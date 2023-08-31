package app.lastpang.hour.domain.schedule.domain.repository;

import app.lastpang.hour.domain.schedule.domain.PersonalSchedule;
import app.lastpang.hour.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalScheduleRepository extends JpaRepository<PersonalSchedule, Long> {
    List<PersonalSchedule> findAllByUser(User user);
}
