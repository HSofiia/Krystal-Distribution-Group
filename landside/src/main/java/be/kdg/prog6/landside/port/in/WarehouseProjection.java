package be.kdg.prog6.landside.port.in;

import be.kdg.prog6.landside.domain.Warehouse;

import java.util.Optional;

@FunctionalInterface
public interface WarehouseProjection {
    Optional<Warehouse> warehouseProjection(WarehouseProjectionCommand warehouseCommand);
}
