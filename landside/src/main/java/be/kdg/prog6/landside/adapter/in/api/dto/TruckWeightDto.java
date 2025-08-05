package be.kdg.prog6.landside.adapter.in.api.dto;

import be.kdg.prog6.landside.domain.TruckWeight;

import java.time.LocalDateTime;
import java.util.UUID;

public class TruckWeightDto{
    private UUID id;
    private String licencePlate;
    private double weight;
    private LocalDateTime time;
    private int warehouseNumber;

    public TruckWeightDto(UUID id, String licencePlate, double weight, LocalDateTime time, int warehouseNumber) {
        this.id = id;
        this.licencePlate = licencePlate;
        this.weight = weight;
        this.time = time;
        this.warehouseNumber = warehouseNumber;
    }

    public static TruckWeightDto of(TruckWeight truckWeight) {
        return new TruckWeightDto(
                UUID.randomUUID(),
                truckWeight.licencePlate().licensePlate(),
                truckWeight.weight(),
                truckWeight.time(),
                truckWeight.warehouseNumber()
        );
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }
}
