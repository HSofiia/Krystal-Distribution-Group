package be.kdg.prog6.landside.adapter.in.dto;

import be.kdg.prog6.landside.domain.TruckArrival;

import java.time.LocalDateTime;

public class TruckArrivalRequestDto {
    private String licensePlate;
    private LocalDateTime arrivalTime;

    public TruckArrivalRequestDto(String licensePlate, LocalDateTime arrivalTime) {
        this.licensePlate = licensePlate;
        this.arrivalTime = arrivalTime;
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

    public static TruckArrivalRequestDto of(TruckArrival truckArrival) {
        return new TruckArrivalRequestDto(
                truckArrival.plate().licensePlate(),
                truckArrival.arrivalTime()
        );
    }
}
