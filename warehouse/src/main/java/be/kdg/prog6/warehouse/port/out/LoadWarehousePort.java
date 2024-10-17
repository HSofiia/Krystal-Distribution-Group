package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.warehouse.domain.Warehouse;

import java.util.Optional;
import java.util.UUID;

@FunctionalInterface
public interface LoadWarehousePort {
    Warehouse loadWarehouseById(UUID warehouseId);
}
