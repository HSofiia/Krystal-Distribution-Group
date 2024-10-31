package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;

import java.util.UUID;

public record WarehouseActivity(int warehouseNumber, SellerId seller, double amount, MaterialType materialType) {
}
