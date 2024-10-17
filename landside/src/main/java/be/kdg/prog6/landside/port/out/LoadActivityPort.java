package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.Activity;
import be.kdg.prog6.landside.domain.ActivityWindow;
import be.kdg.prog6.landside.domain.Appointment;
import be.kdg.prog6.landside.domain.AppointmentStatus;

import java.util.Optional;

@FunctionalInterface
public interface LoadActivityPort {
    Optional<Activity> findByLicencePlateAndStatus(String licencePlate, AppointmentStatus status);

}
