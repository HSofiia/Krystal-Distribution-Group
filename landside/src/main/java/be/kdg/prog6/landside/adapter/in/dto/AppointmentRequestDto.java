package be.kdg.prog6.landside.adapter.in.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentRequestDto {
    private String licensePlate;
    private String materialType;
    private LocalDateTime scheduleDateTime;

    // Getters and setters
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getScheduleDateTime() {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(LocalDateTime scheduleDateTime) {
        this.scheduleDateTime = scheduleDateTime;
    }
}
