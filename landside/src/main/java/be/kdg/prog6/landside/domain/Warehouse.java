package be.kdg.prog6.landside.domain;

import java.util.UUID;


public record Warehouse(UUID warehouseId,
                        int warehouseNumber,
                        MaterialType materialType,
                        boolean isEnoughSpace,
                        SellerId seller,
                        double currentCapacity,
                        double maxCapacity) {
}
