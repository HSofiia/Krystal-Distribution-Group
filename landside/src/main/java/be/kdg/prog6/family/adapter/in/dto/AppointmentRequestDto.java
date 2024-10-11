package be.kdg.prog6.family.adapter.in.dto;

import java.time.LocalDate;

public class AppointmentRequestDto {
    private String licensePlate;
    private String materialType;
    private LocalDate scheduleDateTime;

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

    public LocalDate getScheduleDateTime() {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(LocalDate scheduleDateTime) {
        this.scheduleDateTime = scheduleDateTime;
    }
}
