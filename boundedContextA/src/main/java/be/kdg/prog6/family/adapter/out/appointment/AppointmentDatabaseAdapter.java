package be.kdg.prog6.family.adapter.out.appointment;

import be.kdg.prog6.family.adapter.out.schedule.ScheduleJpaEntity;
import be.kdg.prog6.family.domain.Appointment;
import be.kdg.prog6.family.domain.TruckPlate;
import be.kdg.prog6.family.port.out.AppointmentCreatedPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppointmentDatabaseAdapter implements AppointmentCreatedPort {

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

////    public Optional<Appointment> findAppointmentById(UUID appointmentId) {
////        return appointmentJpaRepository.findAppointmentById(appointmentId)
////                .map(this::toDomainAppointment);
////    }
//
//    private Appointment toDomainAppointment(AppointmentJpaEntity entity) {
//        return new Appointment(
//                new TruckPlate(entity.getLicensePlate()),
//                entity.getMaterialType(),
//                entity.getWarehouseId(),
//                entity.getWarehouseNumber(),
//                entity.getScheduledTime()
//        );
//    }
}


