package be.kdg.prog6.family.adapter.out.appointment;

import be.kdg.prog6.family.domain.Appointment;
import be.kdg.prog6.family.domain.Truck;
import be.kdg.prog6.family.port.out.AppointmentCreatedPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppointmentDatabaseAdapter implements AppointmentCreatedPort {
    private final AppointmentJpaRepository appointmentJpaRepository;

    public AppointmentDatabaseAdapter(AppointmentJpaRepository appointmentJpaRepository) {
        this.appointmentJpaRepository = appointmentJpaRepository;
    }


    @Override
    public void saveAppointment(Appointment appointment, UUID scheduleId) {

        final UUID appointmentId = appointment.getId();
        final AppointmentJpaEntity appointmentJpaEntity = appointmentJpaRepository.findAppointmentById(scheduleId).orElseThrow();
//        piggyBankEntity.getActivities().add(toPiggyBankActivity(piggyBankEntity, activity));
//        piggyBankRepository.save(piggyBankEntity);
        return appointmentJpaRepository.saveAppointment(appointment, scheduleId).ma(this::toAppointment);
    }

    private Appointment toAppointment(final AppointmentJpaEntity appointmentJpaEntity){
        final Truck licencePlate = new Truck(appointmentJpaEntity.getLicensePlate());
        return new Appointment(licencePlate,
                appointmentJpaEntity.getMaterialType(),
                appointmentJpaEntity.getWarehouseId(),
                appointmentJpaEntity.getWarehouseNumber(),
                appointmentJpaEntity.getScheduledTime());
    }
}
