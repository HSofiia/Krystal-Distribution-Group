package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.TruckWeight;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface LoadTruckWeightPort {
    Optional<TruckWeight> loadTruckWeightByLicensePlate(String licensePlate);
}
