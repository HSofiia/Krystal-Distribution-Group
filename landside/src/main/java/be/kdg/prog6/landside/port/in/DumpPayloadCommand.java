package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.TruckPlate;

import java.time.LocalDateTime;

public record DumpPayloadCommand(MaterialType materialType, int warehouseNumber, TruckPlate licencePlate, LocalDateTime time) {
}