package be.kdg.prog6.family.adapter.in.dto;

import be.kdg.prog6.family.domain.Appointment;

import java.time.LocalDateTime;

public record AppointmentDto(LocalDateTime scheduledTime, int warehouseNumber, String truckLicensePlate, String materialType, String status) {

    public static AppointmentDto of(final Appointment appointment) {
        return new AppointmentDto(
                appointment.getScheduledTime(),
                appointment.getWarehouseNumber(),
                appointment.getTruck().licensePlate(),
                appointment.getMaterialType().toString(),
                appointment.getStatus().toString()
        );
    }
}

