package be.kdg.prog6.warehouse.port.in;

import be.kdg.prog6.warehouse.domain.WarehouseActivity;

@FunctionalInterface
public interface WarehouseInfoUseCase {
    WarehouseActivity warehouseInfo(int warehouseNumber);
}
