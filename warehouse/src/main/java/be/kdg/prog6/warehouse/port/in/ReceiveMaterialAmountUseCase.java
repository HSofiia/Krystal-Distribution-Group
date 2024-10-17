package be.kdg.prog6.warehouse.port.in;

import be.kdg.prog6.warehouse.domain.Warehouse;

import java.util.Optional;

@FunctionalInterface
public interface ReceiveMaterialAmountUseCase {
    Optional<Warehouse> receiveMaterial(ReceiveMaterialAmountCommand receiveMaterialCommand);
}
