package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.landside.domain.Appointment;

import java.util.Optional;


@FunctionalInterface
public interface MakeAppointmentUseCase {
    Optional<Appointment> makeAppointment(MakeAppointmentCommand command);
}
