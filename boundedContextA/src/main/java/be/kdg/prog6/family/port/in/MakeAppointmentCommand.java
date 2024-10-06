package be.kdg.prog6.family.port.in;

import be.kdg.prog6.family.domain.MaterialType;
import be.kdg.prog6.family.domain.SellerId;
import be.kdg.prog6.family.domain.TruckPlate;

import java.time.LocalDate;
import java.util.UUID;

public record MakeAppointmentCommand(LocalDate scheduledTime, TruckPlate truckLicensePlate, MaterialType materialType, SellerId sellerId) {
    public MakeAppointmentCommand {
        if (scheduledTime == null || truckLicensePlate == null || materialType == null || sellerId == null) {
            throw new IllegalArgumentException("All fields are required");
        }
    }
}

