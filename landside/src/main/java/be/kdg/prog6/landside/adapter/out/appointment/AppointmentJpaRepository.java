package be.kdg.prog6.landside.adapter.out.appointment;

import be.kdg.prog6.landside.domain.TruckPlate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentJpaEntity, UUID> {
    @Query("SELECT a FROM AppointmentJpaEntity a WHERE a.licensePlate = :licensePlate ORDER BY a.scheduledTime DESC")
    Optional<AppointmentJpaEntity> findLatestAppointmentByLicensePlate(@Param("licensePlate") String licensePlate);
}
