package be.kdg.prog6.landside.port.in;

import java.util.UUID;

public record WarehouseProjectionCommand(int warehouseNumber, boolean isEnoughSpace) {
}
