package be.kdg.prog6.warehouse.port.in;

import be.kdg.prog6.warehouse.domain.Warehouse;

import java.util.List;

public interface WarehouseInfoUseCase {
    Warehouse warehouseInfo(int warehouseNumber);
    List<Warehouse> allWarehousesInfo();
}
