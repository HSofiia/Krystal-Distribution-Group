package be.kdg.prog6.common.event;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.TruckPlate;

import java.time.LocalDateTime;

public record ConveyorPayloadEvent(String materialType, int warehouseNumber, String licencePlate, LocalDateTime time, double netWeight) {
}
