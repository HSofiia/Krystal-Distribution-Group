package be.kdg.prog6.family.port.out;

import be.kdg.prog6.family.domain.Appointment;

import java.util.UUID;

@FunctionalInterface
public interface AppointmentCreatedPort {
    void saveAppointment(Appointment appointment, UUID scheduleId);
}
