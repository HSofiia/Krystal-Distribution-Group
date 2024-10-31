package be.kdg.prog6.common.event;

import be.kdg.prog6.common.domain.ActivityAmountType;

public record ChangeWarehouseCapacityEvent(int warehouseNumber, ActivityAmountType type, double amount) {
}
