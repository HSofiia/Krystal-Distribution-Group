package be.kdg.prog6.landside.adapter.out.appointment;

import be.kdg.prog6.landside.adapter.out.appointmentActivity.AppointmentActivityIdJpaEntity;
import be.kdg.prog6.landside.adapter.out.appointmentActivity.AppointmentActivityJpaEntity;
import be.kdg.prog6.landside.adapter.out.appointmentActivity.AppointmentActivityJpaRepository;
import be.kdg.prog6.landside.adapter.out.schedule.ScheduleJpaEntity;
import be.kdg.prog6.landside.adapter.out.warehouse.WarehouseDatabaseAdapter;
import be.kdg.prog6.landside.adapter.out.warehouse.WarehouseJpaEntity;
import be.kdg.prog6.landside.domain.*;
import be.kdg.prog6.landside.port.out.AppointmentCreatedPort;
import be.kdg.prog6.landside.port.out.LoadAppointmentPort;
import be.kdg.prog6.landside.port.out.UpdatedAppointmentPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class AppointmentDatabaseAdapter implements AppointmentCreatedPort, LoadAppointmentPort, UpdatedAppointmentPort {

    private final AppointmentJpaRepository appointmentJpaRepository;
    private final AppointmentActivityJpaRepository appointmentActivityJpaRepository; // Add this repository for activities
    private final Logger log = getLogger(AppointmentDatabaseAdapter.class);


    public AppointmentDatabaseAdapter(final AppointmentJpaRepository appointmentJpaRepository,
                                      final AppointmentActivityJpaRepository appointmentActivityJpaRepository) {
        this.appointmentJpaRepository = appointmentJpaRepository;
        this.appointmentActivityJpaRepository = appointmentActivityJpaRepository; // Initialize the activity repo
    }

    @Transactional
    @Override
    public void saveAppointment(final Appointment appointment, final UUID scheduleId) {
        log.info("Saving appointment with ID: {} and schedule ID: {}", appointment.getId(), scheduleId);
        AppointmentJpaEntity appointmentEntity = toAppointmentJpaEntity(appointment);
        appointmentEntity.setSchedule(new ScheduleJpaEntity(scheduleId));
        appointmentJpaRepository.save(appointmentEntity);
        log.info("Appointment saved successfully with ID: {}", appointmentEntity.getId());
    }

    @Transactional
    @Override
    public void activityCreated(final Appointment appointment, final Activity activity) {
        final UUID appointmentId = appointment.getId();
        log.info("Creating activity for appointment ID: {}", appointmentId);
        final AppointmentJpaEntity appointmentEntity = appointmentJpaRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found for ID: " + appointmentId));
        log.info("Appointment found. Adding activity: {}", activity.id());
        appointmentEntity.getActivities().add(toAppointmentActivity(appointmentEntity, activity));
        appointmentJpaRepository.save(appointmentEntity);
        log.info("Activity saved successfully for appointment ID: {}", appointmentId);
    }



    private AppointmentActivityJpaEntity toAppointmentActivity(final AppointmentJpaEntity appointmentJpaEntity,
                                                               final Activity activity){
        final AppointmentActivityJpaEntity appointmentActivityJpaEntity = new AppointmentActivityJpaEntity();
        appointmentActivityJpaEntity.setId(AppointmentActivityIdJpaEntity.of(activity.id()));
        appointmentActivityJpaEntity.setActivityType(activity.activityType());
        appointmentActivityJpaEntity.setStatus(activity.status());
        appointmentActivityJpaEntity.setTime(activity.time());
        appointmentActivityJpaEntity.setLicencePlate(activity.licencePlate().licensePlate());
        appointmentActivityJpaEntity.setWarehouseId(new WarehouseJpaEntity(activity.warehouseId()));
        return appointmentActivityJpaEntity;
    }

    @Override
    public Optional<Appointment> findAppointmentByLicencePlate(String licensePlate) {
        return appointmentJpaRepository.findLatestAppointmentByLicensePlate(licensePlate)
                .map(this::toDomainAppointment);
    }


    private static Activity toActivity(final AppointmentActivityJpaEntity activity){
        return new Activity(
                new ActivityId(activity.getId().getAppointmentId(), activity.getId().getActivityId()),
                activity.getActivityType(),
                activity.getTime(),
                activity.getStatus(),
                activity.getWarehouseId().getWarehouseId(),
                new TruckPlate(activity.getLicencePlate())
        );
    }

    private ActivityWindow toActivityWindow(final UUID appointmentId){
        final List<Activity> activities = appointmentActivityJpaRepository
                .findAllById_AppointmentId(appointmentId)
                .stream()
                .map(AppointmentDatabaseAdapter::toActivity)
                .collect(Collectors.toList());
        return new ActivityWindow(appointmentId, activities);
    }


    private AppointmentJpaEntity toAppointmentJpaEntity(final Appointment appointment) {
        return new AppointmentJpaEntity(
                appointment.getId(),
                appointment.getTruck().licensePlate(),
                appointment.getMaterialType(),
                appointment.getWarehouseId(),
                appointment.getWarehouseNumber(),
                appointment.getScheduledTime(),
                appointment.getStatus()
        );
    }

    private Appointment toDomainAppointment(final AppointmentJpaEntity entity) {
        final UUID appointmentId = entity.getId();
        final ActivityWindow activities = toActivityWindow(appointmentId);
        return new Appointment(
                entity.getId(),
                new TruckPlate(entity.getLicensePlate()),
                entity.getMaterialType(),
                entity.getWarehouseId(),
                entity.getWarehouseNumber(),
                entity.getScheduledTime(),
                entity.getStatus(),
                activities
        );
    }
}
