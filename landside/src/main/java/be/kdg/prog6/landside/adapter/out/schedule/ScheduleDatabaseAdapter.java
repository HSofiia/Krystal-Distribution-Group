package be.kdg.prog6.landside.adapter.out.schedule;

import be.kdg.prog6.common.domain.TruckPlate;
import be.kdg.prog6.landside.adapter.out.appointment.AppointmentJpaEntity;
import be.kdg.prog6.landside.adapter.out.appointmentActivity.AppointmentActivityJpaEntity;
import be.kdg.prog6.landside.domain.*;
import be.kdg.prog6.landside.port.out.LoadSchedulePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ScheduleDatabaseAdapter implements LoadSchedulePort {
    private final ScheduleJpaRepository scheduleJpaRepository;

    public ScheduleDatabaseAdapter(ScheduleJpaRepository scheduleJpaRepository) {
        this.scheduleJpaRepository = scheduleJpaRepository;
    }

    @Transactional // Keeps session open for appointments retrieval
    @Override
    public Schedule loadScheduleByDate(LocalDateTime date) {
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
                    appointments);
        } else {
            ScheduleJpaEntity newSchedule = new ScheduleJpaEntity();
            newSchedule.setDate(date);
            scheduleJpaRepository.save(newSchedule);

            // Return the newly created schedule mapped to the domain object
            return toSchedule(newSchedule);
        }
    }


    private Schedule toSchedule(final ScheduleJpaEntity scheduleJpaEntity) {
        List<Appointment> appointments = (scheduleJpaEntity.getScheduledAppointments() != null)
                ? scheduleJpaEntity.getScheduledAppointments()
                .stream()
                .map(this::toDomainAppointment)
                .collect(Collectors.toList())
                : new ArrayList<>();  // If null, return an empty list

        return new Schedule(
                scheduleJpaEntity.getId(),
                scheduleJpaEntity.getDate(),
                appointments
        );
    }

    private Appointment toDomainAppointment(final AppointmentJpaEntity entity) {
        List<Activity> activities = entity.getActivities()
                .stream()
                .map(this::toDomainActivity)  // Convert each JPA activity to a domain activity
                .collect(Collectors.toList());
        ActivityWindow activityWindow = new ActivityWindow(entity.getId(), activities);

        return new Appointment(
                entity.getId(),
                new TruckPlate(entity.getLicensePlate()),
                entity.getMaterialType(),
                entity.getWarehouseId(),
                entity.getWarehouseNumber(),
                entity.getScheduledTime(),
                entity.getStatus(),
                activityWindow
        );
    }

    private Activity toDomainActivity(final AppointmentActivityJpaEntity entity) {
        ActivityId activityId = new ActivityId(
                entity.getId().getAppointmentId(),  // Assuming your composite ID has an appointment ID
                entity.getId().getActivityId()      // Assuming your composite ID has an activity ID
        );

        return new Activity(
                activityId,
                entity.getActivityType(),
                entity.getTime(),
                entity.getStatus(),
                entity.getWarehouseId().getWarehouseId(),
                new TruckPlate(entity.getLicencePlate())  // Map the truck's license plate
        );
    }
}
