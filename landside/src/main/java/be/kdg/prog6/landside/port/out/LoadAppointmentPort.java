package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.Appointment;

import java.util.Optional;

@FunctionalInterface
public interface LoadAppointmentPort {
    Optional<Appointment> findAppointmentByLicencePlate(String licensePlate);
}
