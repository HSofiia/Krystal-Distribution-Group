package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import be.kdg.prog6.common.domain.TruckPlate;

import java.util.UUID;

public record WarehouseReceive(
        int warehouseNumber, MaterialType materialType, SellerId seller, double amountReceived, TruckPlate licensePlate
){}
