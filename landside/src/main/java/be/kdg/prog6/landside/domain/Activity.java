package be.kdg.prog6.landside.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record Activity(UUID id, UUID appointmentId, ActivityType activityType, LocalDateTime time, AppointmentStatus status, UUID warehouseId, TruckPlate licencePlate) {
}
