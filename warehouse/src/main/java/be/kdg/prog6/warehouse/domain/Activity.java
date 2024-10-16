package be.kdg.prog6.warehouse.domain;

import be.kdg.prog6.common.domain.SellerId;

import java.util.UUID;

public record Activity(UUID id, UUID warehouseId, SellerId seller, double receivedAmount, double maxCapacity) {
}
