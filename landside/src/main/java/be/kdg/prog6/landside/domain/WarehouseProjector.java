package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.ActivityType;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;

import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;


public class WarehouseProjector{
    private UUID warehouseId;
    private int warehouseNumber;
    private MaterialType materialType;
    private boolean isEnoughSpace;
    private SellerId seller;
    private double receivedAmount;
    private double maxCapacity;

    public WarehouseProjector(UUID warehouseId, int warehouseNumber, MaterialType materialType, boolean isEnoughSpace, SellerId seller, double receivedAmount, double maxCapacity) {
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.materialType = materialType;
        this.isEnoughSpace = isEnoughSpace;
        this.seller = seller;
        this.receivedAmount = receivedAmount;
        this.maxCapacity = maxCapacity;
    }

    public void modifyCapacity(final ActivityType type, final double amount) {
        if (type == null) {
            throw new IllegalArgumentException("ActivityAmountType cannot be null");
        }
        switch (type) {
            case DELIVERY -> receivedAmount += amount;
            case PURCHASE -> receivedAmount -= amount;
        }

        isEnoughSpace = receivedAmount <= maxCapacity;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
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

    public boolean isEnoughSpace() {
        return isEnoughSpace;
    }

    public void setEnoughSpace(boolean enoughSpace) {
        isEnoughSpace = enoughSpace;
    }

    public SellerId getSeller() {
        return seller;
    }

    public void setSeller(SellerId seller) {
        this.seller = seller;
    }

    public double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
