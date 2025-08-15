package be.kdg.prog6.common.event;

import be.kdg.prog6.common.domain.ActivityType;

public record ChangeWarehouseCapacityEvent(int warehouseNumber, ActivityType type, double amount) {
}
