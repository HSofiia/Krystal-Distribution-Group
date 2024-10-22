package be.kdg.prog6.landside.port.out.appointment;

import be.kdg.prog6.landside.domain.Appointment;

import java.util.UUID;

@FunctionalInterface
public interface AppointmentCreatedPort {
    void saveAppointment(Appointment appointment, UUID scheduleId);
}
