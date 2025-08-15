package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.common.domain.ActivityType;

@FunctionalInterface
public interface WarehouseProjection {
    void warehouseProjection(int warehouseNumber, ActivityType type, double amount);
}
