package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.TruckWeight;

public interface SaveTruckWeightPort {
    void save(TruckWeight truckWeight);
}
