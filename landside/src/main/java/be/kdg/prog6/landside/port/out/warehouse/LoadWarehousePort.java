package be.kdg.prog6.landside.port.out.warehouse;

import be.kdg.prog6.landside.domain.WarehouseProjector;

import java.util.Optional;

@FunctionalInterface
public interface LoadWarehousePort {
    Optional<WarehouseProjector> loadWarehouseByNumber(int warehouseId);
//    Warehouse getWarehouse(MaterialType materialType);
}
