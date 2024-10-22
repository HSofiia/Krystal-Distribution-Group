package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.TruckPlate;

import java.time.LocalDateTime;
import java.util.UUID;

public record TruckWeight(UUID id, TruckPlate licencePlate, double weight, LocalDateTime time, int warehouseNumber) {
}
