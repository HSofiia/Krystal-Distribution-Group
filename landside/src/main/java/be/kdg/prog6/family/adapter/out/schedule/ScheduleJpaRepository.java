package be.kdg.prog6.family.adapter.out.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ScheduleJpaRepository extends JpaRepository<ScheduleJpaEntity, LocalDate> {
    Optional<ScheduleJpaEntity> findScheduleByDate(LocalDate date);
}
