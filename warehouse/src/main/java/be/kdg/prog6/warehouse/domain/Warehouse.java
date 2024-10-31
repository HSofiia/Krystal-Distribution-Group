package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.ActivityAmountType;

public class Warehouse {
    private int warehouseNumber;
    private final WarehouseCurrentCapacity currentCapacity;
    private final ActivityWindow activities;

    public Warehouse(int warehouseNumber, WarehouseCurrentCapacity currentCapacity, ActivityWindow activities) {
        this.warehouseNumber = warehouseNumber;
        this.currentCapacity = currentCapacity;
        this.activities = activities;
    }

    public WarehouseCurrentCapacity calculateCapacity() {
        return activities.calculateCapacity(currentCapacity);
    }

    public PayloadActivity putMaterial(final double amount) {
        return activities.addActivity(ActivityAmountType.DELIVERY, amount);
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public WarehouseCurrentCapacity getCurrentCapacity() {
        return currentCapacity;
    }

    public ActivityWindow getActivities() {
        return activities;
    }
}
