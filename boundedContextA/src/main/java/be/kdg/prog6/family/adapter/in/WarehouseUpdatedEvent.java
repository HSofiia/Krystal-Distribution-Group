package be.kdg.prog6.family.adapter.in;

import java.util.UUID;

public record WarehouseUpdatedEvent(UUID warehouseId, boolean isEnoughSpace) {
}
