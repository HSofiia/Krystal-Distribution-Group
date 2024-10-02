package be.kdg.prog6.family.port.in;

import be.kdg.prog6.family.domain.MaterialType;
import be.kdg.prog6.family.domain.SellerId;
import be.kdg.prog6.family.domain.Truck;
import be.kdg.prog6.family.domain.Warehouse;

import java.time.LocalDateTime;

public record MakeAppointmentCommand(LocalDateTime scheduledTime, Truck truckLicensePlate, MaterialType materialType, Warehouse warehouseId, SellerId sellerId) {
    public MakeAppointmentCommand {
        if (scheduledTime == null || truckLicensePlate == null || materialType == null || warehouseId == null) {
            throw new IllegalArgumentException("All fields are required");
        }
    }
}
