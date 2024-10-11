package be.kdg.prog6.family.port.out;

import be.kdg.prog6.family.domain.Appointment;
import be.kdg.prog6.family.domain.TruckPlate;

import java.util.Optional;

@FunctionalInterface
public interface LoadAppointmentPort {
    Optional<Appointment> findAppointmentByLicencePlate(String licensePlate);
}
