package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;

import java.util.UUID;

public class Warehouse {
    UUID warehouseId;
    int warehouseNumber;
    MaterialType materialType;
    SellerId seller;
    double receivedAmount; // -25 ton, +30 ton
    double maxCapacity;

}
