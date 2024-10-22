package be.kdg.prog6.landside.port.out.truck;

import be.kdg.prog6.common.domain.TruckPlate;

@FunctionalInterface
public interface LoadTruckPort {
    TruckPlate loadTruckByLicensePlate(String licensePlate);
}
