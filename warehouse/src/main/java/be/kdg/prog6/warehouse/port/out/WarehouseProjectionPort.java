package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.common.event.ChangeWarehouseCapacityEvent;

public interface WarehouseProjectionPort {
    void warehouseCapacityProjection(ChangeWarehouseCapacityEvent event);
}
