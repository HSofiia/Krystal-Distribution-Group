package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.TruckPlate;

import java.time.LocalDateTime;
import java.util.UUID;

public record Activity(ActivityId id, ActivityType activityType, LocalDateTime time, TruckStatus status, UUID warehouseId, TruckPlate licencePlate) {
}
