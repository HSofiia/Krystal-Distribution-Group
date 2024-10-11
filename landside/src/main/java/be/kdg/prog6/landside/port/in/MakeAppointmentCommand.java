package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.landside.domain.MaterialType;
import be.kdg.prog6.landside.domain.SellerId;
import be.kdg.prog6.landside.domain.TruckPlate;

import java.time.LocalDate;

public record MakeAppointmentCommand(LocalDate scheduledTime, TruckPlate truckLicensePlate, MaterialType materialType, SellerId sellerId) {
    public MakeAppointmentCommand {
        if (scheduledTime == null || truckLicensePlate == null || materialType == null || sellerId == null) {
            throw new IllegalArgumentException("All fields are required");
        }
    }
}

