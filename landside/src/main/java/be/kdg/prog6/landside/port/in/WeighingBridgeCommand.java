package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.common.domain.TruckPlate;

import java.time.LocalDateTime;

public record WeighingBridgeCommand(String licencePlate, double weight, LocalDateTime time, int warehouseNumber) {
}
