package be.kdg.prog6.landside.adapter.in;

import java.util.UUID;

public record WarehouseUpdatedEvent(UUID warehouseId, boolean isEnoughSpace) {

}
// date, amount, warehouse nm