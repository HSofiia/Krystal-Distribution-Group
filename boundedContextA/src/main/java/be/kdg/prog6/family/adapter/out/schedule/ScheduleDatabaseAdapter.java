package be.kdg.prog6.family.adapter.out.schedule;

import be.kdg.prog6.family.adapter.out.appointment.AppointmentJpaEntity;
import be.kdg.prog6.family.domain.Appointment;
import be.kdg.prog6.family.domain.Schedule;
import be.kdg.prog6.family.domain.TruckPlate;
import be.kdg.prog6.family.port.out.LoadSchedulePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ScheduleDatabaseAdapter implements LoadSchedulePort {
    private final ScheduleJpaRepository scheduleJpaRepository;

    public ScheduleDatabaseAdapter(ScheduleJpaRepository scheduleJpaRepository) {
        this.scheduleJpaRepository = scheduleJpaRepository;
    }

    /**
     * This method is transactional to ensure that the session remains open while the schedule
     * and associated appointments are being loaded.
     */
    @Transactional // Keeps session open for appointments retrieval
    @Override
    public Schedule loadScheduleByDate(LocalDate date) {
        Optional<ScheduleJpaEntity> scheduleJpaEntityOpt = scheduleJpaRepository.findScheduleByDate(date);

        if (scheduleJpaEntityOpt.isPresent()) {
            ScheduleJpaEntity scheduleJpaEntity = scheduleJpaEntityOpt.get();

            // Convert appointments from JPA to domain within the same transaction to avoid lazy initialization
            List<Appointment> appointments = scheduleJpaEntity.getScheduledAppointments()
                    .stream()
                    .map(this::toDomainAppointment)
                    .collect(Collectors.toList());

            return new Schedule(
                    scheduleJpaEntity.getId(),
                    scheduleJpaEntity.getDate(),
                    appointments,
                    scheduleJpaEntity.getMaxTrucksPerHour()
            );
        } else {
            throw new IllegalArgumentException("Schedule not found for the date: " + date);
        }
    }

    /**
     * Converts the AppointmentJpaEntity to the domain Appointment object.
     */
    private Appointment toDomainAppointment(final AppointmentJpaEntity entity) {
        return new Appointment(
                new TruckPlate(entity.getLicensePlate()),
                entity.getMaterialType(),
                entity.getWarehouseId(),
                entity.getWarehouseNumber(),
                entity.getScheduledTime()
        );
    }
}
