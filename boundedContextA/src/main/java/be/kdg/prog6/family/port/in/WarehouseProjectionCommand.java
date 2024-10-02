package be.kdg.prog6.family.port.in;

import java.util.UUID;

public record WarehouseProjectionCommand(UUID warehouseId, boolean isEnoughSpace) {
}
