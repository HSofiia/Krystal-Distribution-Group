package be.kdg.prog6.family.port.in;

import be.kdg.prog6.family.domain.Appointment;

import java.util.Optional;


@FunctionalInterface
public interface MakeAppointmentUseCase {
    Optional<Appointment> makeAppointment(MakeAppointmentCommand command);
}
