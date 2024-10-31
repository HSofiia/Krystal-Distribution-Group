package be.kdg.prog6.warehouse.port.in;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import be.kdg.prog6.common.domain.TruckPlate;

import java.util.UUID;

public record ReceiveMaterialAmountCommand(int warehouseNumber, MaterialType materialType, double amountReceived) {
}
