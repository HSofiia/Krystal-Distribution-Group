package be.kdg.prog6.landside.adapter.out.appointment;

import be.kdg.prog6.landside.adapter.out.schedule.ScheduleJpaEntity;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.TruckPlate;
import be.kdg.prog6.landside.port.out.AppointmentCreatedPort;
import be.kdg.prog6.landside.port.out.LoadAppointmentPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AppointmentDatabaseAdapter implements AppointmentCreatedPort, LoadAppointmentPort {

    private final AppointmentJpaRepository appointmentJpaRepository;

    public AppointmentDatabaseAdapter(final AppointmentJpaRepository appointmentJpaRepository) {
        this.appointmentJpaRepository = appointmentJpaRepository;
    }

    @Override
    public void saveAppointment(final Appointment appointment, final UUID scheduleId) {
        AppointmentJpaEntity appointmentEntity = toAppointmentJpaEntity(appointment);
        appointmentEntity.setSchedule(new ScheduleJpaEntity(scheduleId));
        appointmentJpaRepository.save(appointmentEntity);
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

    @Override
    public Optional<Appointment> findAppointmentByLicencePlate(String licensePlate) {
        return appointmentJpaRepository.findAppointmentJpaEntityByLicensePlate(licensePlate)
                .map(this::toDomainAppointment);
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


