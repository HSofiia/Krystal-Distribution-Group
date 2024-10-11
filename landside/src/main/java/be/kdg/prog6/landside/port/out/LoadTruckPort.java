package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.TruckPlate;

import java.util.Optional;

@FunctionalInterface
public interface LoadTruckPort {
    Optional<TruckPlate> loadTruckByLicensePlate(String licensePlate);
}
