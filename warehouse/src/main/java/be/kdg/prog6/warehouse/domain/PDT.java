package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.TruckPlate;

import java.time.LocalDateTime;

public record PDT(MaterialType materialType, int warehouseNumber, TruckPlate licencePlate, LocalDateTime time, double netWeight) {
}
