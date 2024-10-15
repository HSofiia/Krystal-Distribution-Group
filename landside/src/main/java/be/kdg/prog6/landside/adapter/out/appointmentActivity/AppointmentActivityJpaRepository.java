package be.kdg.prog6.landside.adapter.out.appointmentActivity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentActivityJpaRepository extends JpaRepository<AppointmentActivityJpaEntity, AppointmentActivityIdJpaEntity> {
    List<AppointmentActivityJpaEntity> findAllById_AppointmentId(UUID appointmentId);
}
