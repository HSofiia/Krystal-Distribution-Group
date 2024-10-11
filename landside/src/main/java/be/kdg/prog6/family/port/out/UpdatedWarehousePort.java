package be.kdg.prog6.family.port.out;

import be.kdg.prog6.family.domain.Warehouse;

@FunctionalInterface
public interface UpdatedWarehousePort {
    void updateWarehouse(Warehouse warehouse);
}
