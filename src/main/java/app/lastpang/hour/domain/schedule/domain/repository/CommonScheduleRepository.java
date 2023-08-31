package app.lastpang.hour.domain.schedule.domain.repository;

import app.lastpang.hour.domain.schedule.domain.CommonSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonScheduleRepository extends JpaRepository<CommonSchedule, Long> {
}
