package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.TruckPlate;
import be.kdg.prog6.landside.domain.TruckStatus;

import java.time.LocalDateTime;

public record DumpPayloadCommand(TruckPlate licencePlate, LocalDateTime time) {
}