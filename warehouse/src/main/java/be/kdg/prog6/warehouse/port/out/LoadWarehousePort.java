package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.warehouse.domain.Warehouse;


@FunctionalInterface
public interface LoadWarehousePort {
    Warehouse loadWarehouseByNumberSnapshot(int warehouseNumber);
}
