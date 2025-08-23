package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.ActivityType;
import be.kdg.prog6.common.domain.MaterialType;

import java.time.LocalDateTime;
import java.util.Optional;

public class Warehouse {
    private int warehouseNumber;
    private MaterialType materialType;
    private final WarehouseCurrentCapacity currentCapacity;
    private final ActivityWindow activities;
    private final Seller seller;

    public Warehouse(int warehouseNumber, MaterialType materialType, WarehouseCurrentCapacity currentCapacity, ActivityWindow activities, Seller seller
    ) {
        this.warehouseNumber = warehouseNumber;
        this.materialType = materialType;
        this.currentCapacity = currentCapacity;
        this.activities = activities;
        this.seller = seller;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public WarehouseCurrentCapacity getCurrentCapacity() {
        return currentCapacity;
    }

    public ActivityWindow getActivities() {
        return activities;
    }

    public Seller getSeller() {
        return seller;
    }

    public WarehouseCurrentCapacity calculateCapacity() {
        LocalDateTime snapshot = this.currentCapacity.time() != null
                ? this.currentCapacity.time()
                : LocalDateTime.MIN;

        double addedWeight = activities.addChangeToCapacitySnapshot(snapshot);
        Optional<LocalDateTime> lastOptional = activities.findLastActivitySnapshot(snapshot);
        LocalDateTime last = lastOptional.orElse(snapshot);
        return new WarehouseCurrentCapacity(currentCapacity.number() + addedWeight, last);
    }


    public PayloadActivity putMaterial(final double amount, LocalDateTime time) {
        return activities.addActivity(ActivityType.DELIVERY, amount, time);
    }

    public Optional<PayloadActivity> isPendingPayloadActivity() {
        return activities.findPendingPayloadActivity();
    }

    public PayloadActivity createDeliveryActivity(double amount, LocalDateTime time) {
        return activities.addActivity(ActivityType.DELIVERY, amount, time);
    }
}
