package be.kdg.prog6.family.port.in;

import be.kdg.prog6.family.domain.Warehouse;

import java.util.Optional;

@FunctionalInterface
public interface WarehouseProjection {
    Optional<Warehouse> warehouseProjection(WarehouseProjectionCommand warehouseCommand);
}
