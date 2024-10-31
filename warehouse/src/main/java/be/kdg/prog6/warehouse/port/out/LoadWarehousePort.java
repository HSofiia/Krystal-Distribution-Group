package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.domain.WarehouseReceive;

import java.util.Optional;

@FunctionalInterface
public interface LoadWarehousePort {
    Optional<Warehouse> loadWarehouseByNumber(int warehouseNumber);
}
