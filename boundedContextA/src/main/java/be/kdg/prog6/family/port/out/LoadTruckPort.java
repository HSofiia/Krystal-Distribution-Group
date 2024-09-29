package be.kdg.prog6.family.port.out;

import be.kdg.prog6.family.domain.Truck;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface LoadTruckPort {
    Optional<Truck> loadTruckByLicensePlate(String licensePlate);
}
