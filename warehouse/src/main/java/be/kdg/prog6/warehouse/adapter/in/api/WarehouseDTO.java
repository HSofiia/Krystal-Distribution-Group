package be.kdg.prog6.warehouse.adapter.in.api;

import be.kdg.prog6.warehouse.domain.Warehouse;


public record WarehouseDTO(int warehouseNumber, String seller, double receivedAmount, double maxCapacity, String materialType){
    public static WarehouseDTO from(Warehouse warehouse) {
        var computed = warehouse.calculateCapacity(); // ← adds activities since snapshot
        return new WarehouseDTO(
                warehouse.getWarehouseNumber(),
                warehouse.getSeller().getName(),
                computed.capacity(),                // ← was getCurrentCapacity().*
                warehouse.getMaxCapacity(),
                java.util.Arrays.stream(warehouse.getMaterialType().name().toLowerCase().split("_"))
                        .map(w -> w.substring(0,1).toUpperCase() + w.substring(1))
                        .collect(java.util.stream.Collectors.joining(" "))
        );
    }
}
