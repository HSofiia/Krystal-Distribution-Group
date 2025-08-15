package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.warehouse.domain.Warehouse;

@FunctionalInterface
public interface UpdateWarehouseCapacityPort {
    void updateWarehouse(Warehouse warehouse);
}
