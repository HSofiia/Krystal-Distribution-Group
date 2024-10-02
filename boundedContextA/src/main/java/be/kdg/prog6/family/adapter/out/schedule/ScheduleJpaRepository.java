package be.kdg.prog6.family.adapter.out.schedule;

import be.kdg.prog6.family.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ScheduleJpaRepository extends JpaRepository<Schedule, LocalDateTime> {
    Optional<ScheduleJpaEntity> findScheduleByDate(LocalDateTime date);
}