package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.TruckPlate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Appointment {
    private final UUID id;
    private final TruckPlate truck;
    private final MaterialType materialType;
    private final UUID warehouseId;
    private final int warehouseNumber;
    private final LocalDateTime scheduledTime;
    private AppointmentStatus status;
    private final ActivityWindow activityWindow;
    private final List<Activity> activities;

    public Appointment(UUID id, TruckPlate licensePlate, MaterialType materialType, UUID warehouseId, int warehouseNumber, LocalDateTime scheduledTime, AppointmentStatus status, ActivityWindow activityWindow) {
        this.id = id;
        this.truck = licensePlate;
        this.materialType = materialType;
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.scheduledTime = scheduledTime;
        this.status = status;
        this.activityWindow = activityWindow;
        this.activities = new ArrayList<>();
    }

    public Appointment(UUID id, TruckPlate licensePlate, MaterialType materialType, UUID warehouseId, int warehouseNumber, LocalDateTime scheduledTime, AppointmentStatus status, ActivityWindow activityWindow, List<Activity> activities) {
        this.id = id;
        this.truck = licensePlate;
        this.materialType = materialType;
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.scheduledTime = scheduledTime;
        this.status = status;
        this.activityWindow = activityWindow;
        this.activities = activities;
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

    public ActivityWindow getActivityWindow() {
        return activityWindow;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public Activity addActivity(ActivityType activityType, TruckStatus status, LocalDateTime dateTime) {
        Activity activity = new Activity(
                new ActivityId(this.id, UUID.randomUUID()),
                activityType,
                dateTime,
                status,
                this.warehouseId,
                this.truck
        );
        this.activities.add(activity);

        if (activityType == ActivityType.ARRIVED && status == TruckStatus.ARRIVED) {
            this.status = AppointmentStatus.ARRIVED_ON_TIME;
        }

        return activity;
    }
}
