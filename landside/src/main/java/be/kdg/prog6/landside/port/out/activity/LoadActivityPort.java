package be.kdg.prog6.landside.port.out.activity;

import be.kdg.prog6.landside.domain.Activity;
import be.kdg.prog6.landside.domain.AppointmentStatus;

import java.util.Optional;

@FunctionalInterface
public interface LoadActivityPort {
    Optional<Activity> findByLicencePlateAndStatus(String licencePlate, AppointmentStatus status);

}
