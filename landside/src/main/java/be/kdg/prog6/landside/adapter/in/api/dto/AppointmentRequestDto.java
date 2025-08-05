package be.kdg.prog6.landside.adapter.in.api.dto;

import java.time.LocalDateTime;

public class AppointmentRequestDto {
    private String licensePlate;
    private String materialType;
    private LocalDateTime scheduleDateTime;

    public AppointmentRequestDto(String licensePlate, String materialType, LocalDateTime scheduleDateTime) {
        this.licensePlate = licensePlate;
        this.materialType = materialType;
        this.scheduleDateTime = scheduleDateTime;
    }

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
