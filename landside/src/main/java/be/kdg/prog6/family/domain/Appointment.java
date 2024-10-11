package be.kdg.prog6.family.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {
    private final UUID id;
    private final TruckPlate truck;
    private final MaterialType materialType;
    private final UUID warehouseId;
    private final int warehouseNumber;
    private final LocalDateTime scheduledTime;
    private AppointmentStatus status;

    public Appointment(TruckPlate licensePlate, MaterialType materialType, UUID warehouseId, int warehouseNumber, LocalDateTime scheduledTime) {
        this.id = UUID.randomUUID();
        this.truck = licensePlate;
        this.materialType = materialType;
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.scheduledTime = scheduledTime;
        this.status = AppointmentStatus.SCHEDULED;  // Default status as 'SCHEDULED'
    }

    public UUID getId() {
        return id;
    }

    public TruckPlate getTruck() {
        return truck;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}