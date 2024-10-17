package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import be.kdg.prog6.common.domain.TruckPlate;

import java.time.LocalDateTime;

public record MakeAppointmentCommand(LocalDateTime scheduledTime, TruckPlate truckLicensePlate, MaterialType materialType, SellerId sellerId) {
    public MakeAppointmentCommand {
        if (scheduledTime == null || truckLicensePlate == null || materialType == null || sellerId == null) {
            throw new IllegalArgumentException("All fields are required");
        }
    }
}

