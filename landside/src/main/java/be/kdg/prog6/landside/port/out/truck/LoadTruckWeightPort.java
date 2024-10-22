package be.kdg.prog6.landside.port.out.truck;

import be.kdg.prog6.landside.domain.TruckWeight;

import java.util.Optional;

@FunctionalInterface
public interface LoadTruckWeightPort {
    Optional<TruckWeight> loadTruckWeightByLicensePlate(String licensePlate);
}
