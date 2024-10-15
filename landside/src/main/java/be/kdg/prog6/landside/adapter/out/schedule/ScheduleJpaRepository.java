package be.kdg.prog6.landside.adapter.out.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ScheduleJpaRepository extends JpaRepository<ScheduleJpaEntity, LocalDateTime> {
    Optional<ScheduleJpaEntity> findScheduleByDate(LocalDateTime date);
}
