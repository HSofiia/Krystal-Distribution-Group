package be.kdg.prog6.family.port.out;

import be.kdg.prog6.family.domain.MaterialType;
import be.kdg.prog6.family.domain.SellerId;
import be.kdg.prog6.family.domain.Warehouse;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface LoadWarehousePort {
    Optional<Warehouse> loadWarehouseById(UUID warehouseId);
//    Warehouse getWarehouse(MaterialType materialType);
}
