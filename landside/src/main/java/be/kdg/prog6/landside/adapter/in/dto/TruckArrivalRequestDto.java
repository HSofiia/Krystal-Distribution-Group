package be.kdg.prog6.landside.adapter.in.dto;

import be.kdg.prog6.landside.domain.TruckArrival;

import java.time.LocalDateTime;

public class TruckArrivalRequestDto {
    private String licensePlate;
    private LocalDateTime arrivalTime;
    private int weighingBridgeNumber;

    public TruckArrivalRequestDto(String licensePlate, LocalDateTime arrivalTime, int weighingBridgeNumber) {
        this.licensePlate = licensePlate;
        this.arrivalTime = arrivalTime;
        this.weighingBridgeNumber = weighingBridgeNumber;
    }

    // Getters and setters
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getWeighingBridgeNumber() {
        return weighingBridgeNumber;
    }

    public void setWeighingBridgeNumber(int weighingBridgeNumber) {
        this.weighingBridgeNumber = weighingBridgeNumber;
    }

    public static TruckArrivalRequestDto of(TruckArrival truckArrival) {
        return new TruckArrivalRequestDto(
                truckArrival.plate().licensePlate(),
                truckArrival.arrivalTime(),
                truckArrival.weighingBridgeNumber()
        );
    }
}
