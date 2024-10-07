package be.kdg.prog6.family.adapter.out.schedule;

import be.kdg.prog6.family.adapter.out.appointment.AppointmentJpaEntity;
import be.kdg.prog6.family.domain.Appointment;
import be.kdg.prog6.family.domain.Schedule;
import be.kdg.prog6.family.domain.TruckPlate;
import be.kdg.prog6.family.port.out.LoadSchedulePort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleDatabaseAdapter implements LoadSchedulePort {
    private final ScheduleJpaRepository scheduleJpaRepository;

    public ScheduleDatabaseAdapter(ScheduleJpaRepository scheduleJpaRepository) {
        this.scheduleJpaRepository = scheduleJpaRepository;
    }


    @Override
    public Schedule loadScheduleByDate(LocalDate date) {
        return scheduleJpaRepository.findScheduleByDate(date)
                .map(this::toSchedule)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
    }

    private Schedule toSchedule(final ScheduleJpaEntity scheduleJpaEntity){
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
    }

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
