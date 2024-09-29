package be.kdg.prog6.family.domain;

import java.time.LocalDateTime;

public class Appointment {
    private String id;
    private LocalDateTime scheduledTime;
    private Truck truck;
    private Material material;
    private AppointmentStatus status;

    public Appointment(String id, LocalDateTime scheduledTime, Truck truck, Material material, AppointmentStatus status) {
        this.id = id;
        this.scheduledTime = scheduledTime;
        this.truck = truck;
        this.material = material;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
