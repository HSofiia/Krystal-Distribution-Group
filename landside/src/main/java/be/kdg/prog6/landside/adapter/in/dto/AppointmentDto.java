package be.kdg.prog6.landside.adapter.in.dto;

import be.kdg.prog6.landside.adapter.out.appointment.AppointmentJpaEntity;
import be.kdg.prog6.landside.domain.Appointment;

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

    public static AppointmentDto of(AppointmentJpaEntity entity) {
        return new AppointmentDto(
                entity.getScheduledTime(),
                entity.getWarehouseNumber(),
                entity.getLicensePlate(),
                entity.getMaterialType().toString(),
                entity.getStatus().toString()
    );
    }
}

