package be.kdg.prog6.family.domain;

import java.util.UUID;

public class Warehouse {
    private UUID warehouseId;
    private double capacity;
    private boolean isBelowEightyPercent;

    public Warehouse(UUID warehouseId, double capacity, boolean isBelowEightyPercent) {
        this.warehouseId = warehouseId;
        this.capacity = capacity;
        this.isBelowEightyPercent = isBelowEightyPercent;
    }

    public void checkIfBelowEightyPercent(double currentCapacity) {
        this.isBelowEightyPercent = currentCapacity < this.capacity * 0.8;
    }

    public boolean isBelowEightyPercent() {
        return isBelowEightyPercent;
    }

    public void setBelowEightyPercent(boolean belowEightyPercent) {
        isBelowEightyPercent = belowEightyPercent;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID id) {
        this.warehouseId = id;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }


}
