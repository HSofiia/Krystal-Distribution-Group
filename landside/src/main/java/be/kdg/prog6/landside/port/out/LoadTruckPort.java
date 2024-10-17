package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.common.domain.TruckPlate;

import java.util.Optional;

@FunctionalInterface
public interface LoadTruckPort {
    TruckPlate loadTruckByLicensePlate(String licensePlate);
}
