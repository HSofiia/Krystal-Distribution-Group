package be.kdg.prog6.family.adapter.out.appointment;

import be.kdg.prog6.family.adapter.out.schedule.ScheduleJpaEntity;
import be.kdg.prog6.family.domain.Appointment;
import be.kdg.prog6.family.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppointmentJpaRepository extends JpaRepository<Appointment, UUID> {
    void saveAppointment(Appointment appointment, UUID scheduleId);

    Optional<AppointmentJpaEntity> findAppointmentById(UUID appointmentId);

}
