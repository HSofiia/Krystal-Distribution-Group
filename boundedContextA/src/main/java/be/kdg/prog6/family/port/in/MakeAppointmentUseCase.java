package be.kdg.prog6.family.port.in;

import be.kdg.prog6.family.domain.Appointment;

import java.time.LocalDateTime;

@FunctionalInterface
public interface MakeAppointmentUseCase {
    Appointment makeAppointment(MakeAppointmentCommand command);

}
