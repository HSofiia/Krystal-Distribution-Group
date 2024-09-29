package be.kdg.prog6.family.port.in;

import java.time.LocalDateTime;
import java.util.UUID;

public record MakeAppointmentCommand(LocalDateTime scheduledTime, String truckLicensePlate, String materialName, UUID warehouseId) {
    public MakeAppointmentCommand {
        if (scheduledTime == null || truckLicensePlate == null || materialName == null || warehouseId == null) {
            throw new IllegalArgumentException("All fields are required");
        }
    }
}
