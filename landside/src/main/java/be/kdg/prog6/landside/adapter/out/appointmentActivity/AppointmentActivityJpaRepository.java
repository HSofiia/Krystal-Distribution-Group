package be.kdg.prog6.landside.adapter.out.appointmentActivity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AppointmentActivityJpaRepository extends JpaRepository<AppointmentActivityJpaEntity, AppointmentActivityIdJpaEntity> {
    List<AppointmentActivityJpaEntity> findAllById_AppointmentId(UUID appointmentId);

    @Query("SELECT a FROM AppointmentActivityJpaEntity a WHERE a.status = 'ON_SITE'")
    List<AppointmentActivityJpaEntity> findAllByActivityTypeOnSite();
}
