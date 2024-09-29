package be.kdg.prog6.family.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Schedule {
    private UUID scheduleId;
    private LocalDateTime scheduledTime;
    private Truck truck;
    private Material material;

    public Schedule(UUID scheduleId, LocalDateTime scheduledTime, Truck truck, Material material) {
        this.scheduleId = scheduleId;
        this.scheduledTime = scheduledTime;
        this.truck = truck;
        this.material = material;
    }

    public UUID getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(UUID scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
