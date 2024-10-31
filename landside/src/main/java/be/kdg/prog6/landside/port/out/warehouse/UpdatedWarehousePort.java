package be.kdg.prog6.landside.port.out.warehouse;

import be.kdg.prog6.landside.domain.WarehouseProjector;

@FunctionalInterface
public interface UpdatedWarehousePort {
    void updateWarehouse(WarehouseProjector warehouse);
}
