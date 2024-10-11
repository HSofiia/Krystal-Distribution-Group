package be.kdg.prog6.landside.adapter.out.appointment;

import be.kdg.prog6.landside.domain.TruckPlate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentJpaEntity, UUID> {
    Optional<AppointmentJpaEntity> findAppointmentById(UUID appointmentId);

    Optional<AppointmentJpaEntity> findAppointmentJpaEntityByLicensePlate(String licensePlate);
}
