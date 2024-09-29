package be.kdg.prog6.family.domain;

import java.util.UUID;

public class Truck {
    private UUID truckId;
    private String licensePlate;
    private double weightCapacity; // Max payload capacity in tons

    public Truck(UUID truckId, String licensePlate, double weightCapacity) {
        this.truckId = truckId;
        this.licensePlate = licensePlate;
        this.weightCapacity = weightCapacity;
    }

    public UUID getTruckId() {
        return truckId;
    }

    public void setTruckId(UUID truckId) {
        this.truckId = truckId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(double weightCapacity) {
        this.weightCapacity = weightCapacity;
    }
}
