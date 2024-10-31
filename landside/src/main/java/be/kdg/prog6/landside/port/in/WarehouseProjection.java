package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.common.domain.ActivityAmountType;

@FunctionalInterface
public interface WarehouseProjection {
    void warehouseProjection(int warehouseNumber, ActivityAmountType type, double amount);
}
