package be.kdg.prog6.landside.domain;

import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;

import java.util.UUID;


public record Warehouse(UUID warehouseId,
                        int warehouseNumber,
                        MaterialType materialType,
                        boolean isEnoughSpace,
                        SellerId seller,
                        double receivedAmount,
                        double maxCapacity) {
}
