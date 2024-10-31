package be.kdg.prog6.warehouse.port.in;

import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.domain.WarehouseCurrentCapacity;
import be.kdg.prog6.warehouse.domain.WarehouseReceive;

import java.util.Optional;

@FunctionalInterface
public interface ReceiveMaterialAmountUseCase {
    WarehouseCurrentCapacity receiveMaterial(ReceiveMaterialAmountCommand receiveMaterialCommand);
}
