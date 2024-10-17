package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.TruckPlate;

import java.time.LocalDateTime;
import java.util.UUID;

public record Activity(ActivityId id, ActivityType activityType, LocalDateTime time, AppointmentStatus status, UUID warehouseId, TruckPlate licencePlate) {
}
