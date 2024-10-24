package be.kdg.prog6.landside.port.in;

import java.util.UUID;

public record WarehouseProjectionCommand(UUID warehouseId, boolean isEnoughSpace) {
    public WarehouseProjectionCommand {
        if (warehouseId == null) {
            throw new IllegalArgumentException("warehouse id is required");
        }
    }
}
