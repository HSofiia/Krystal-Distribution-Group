package be.kdg.prog6.landside.port.out.warehouse;

import be.kdg.prog6.landside.domain.Warehouse;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface LoadWarehousePort {
    Optional<Warehouse> loadWarehouseById(UUID warehouseId);
//    Warehouse getWarehouse(MaterialType materialType);
}
