package be.kdg.prog6.warehouse.port.in;

import be.kdg.prog6.warehouse.domain.WarehouseCurrentCapacity;

import java.util.Optional;

@FunctionalInterface
public interface ReceiveMaterialAmountUseCase {
    WarehouseCurrentCapacity receiveMaterial(ReceiveMaterialAmountCommand receiveMaterialCommand);
}
