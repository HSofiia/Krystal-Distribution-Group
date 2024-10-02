package be.kdg.prog6.family.domain;

import java.util.UUID;


public record Warehouse(UUID warehouseId,
                        int warehouseNumber,
                        MaterialType materialType,
                        double capacity,
                        boolean isEnoughSpace,
                        SellerId seller) { }
