package be.kdg.prog6.family.adapter.out.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentJpaEntity, UUID> {
    void saveAppointment(AppointmentJpaEntity appointment, UUID scheduleId);

    Optional<AppointmentJpaEntity> findAppointmentById(UUID appointmentId);

}
