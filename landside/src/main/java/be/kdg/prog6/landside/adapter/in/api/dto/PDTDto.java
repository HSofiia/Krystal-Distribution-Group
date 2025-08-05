package be.kdg.prog6.landside.adapter.in.api.dto;

import java.time.LocalDateTime;

public class PDTDto {
    private int warehouseNumber;
    private LocalDateTime deliveryTime;
    private String materialType;
    private int dockNumber;

    public PDTDto(int warehouseNumber, LocalDateTime deliveryDateTime, String materialType, int dockNumber) {
        this.warehouseNumber = warehouseNumber;
        this.deliveryTime = deliveryDateTime;
        this.materialType = materialType;
        this.dockNumber = dockNumber;
    }


    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }
}
