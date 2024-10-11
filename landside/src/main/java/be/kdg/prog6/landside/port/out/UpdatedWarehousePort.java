package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.landside.domain.Warehouse;

@FunctionalInterface
public interface UpdatedWarehousePort {
    void updateWarehouse(Warehouse warehouse);
}
