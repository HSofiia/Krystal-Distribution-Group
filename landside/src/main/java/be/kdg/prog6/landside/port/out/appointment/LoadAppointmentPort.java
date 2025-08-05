package be.kdg.prog6.landside.port.out.appointment;

import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.AppointmentStatus;
import be.kdg.prog6.landside.domain.TruckStatus;

import java.util.Optional;
import java.util.UUID;

public interface LoadAppointmentPort {
    Optional<Appointment> findAppointmentByLicencePlate(String licensePlate);
    Appointment findAppointmentByLicencePlateAndStatus(String licensePlate, AppointmentStatus status);
}
