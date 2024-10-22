package be.kdg.prog6.landside.port.out.appointment;

import be.kdg.prog6.landside.domain.Appointment;

import java.util.Optional;
import java.util.UUID;

public interface LoadAppointmentPort {
    Optional<Appointment> findAppointmentByLicencePlate(String licensePlate);
}
