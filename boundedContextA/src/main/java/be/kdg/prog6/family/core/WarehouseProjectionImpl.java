package be.kdg.prog6.family.core;

import be.kdg.prog6.family.domain.Warehouse;
import be.kdg.prog6.family.port.in.WarehouseProjection;
import be.kdg.prog6.family.port.in.WarehouseProjectionCommand;
import be.kdg.prog6.family.port.out.LoadWarehousePort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseProjectionImpl implements WarehouseProjection {

    private final LoadWarehousePort loadWarehousePort;

    public WarehouseProjectionImpl(LoadWarehousePort loadWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    public Optional<Warehouse> warehouseProjection(WarehouseProjectionCommand warehouseCommand) {
        Optional<Warehouse> optionalWarehouse = loadWarehousePort.loadWarehouseById(warehouseCommand.warehouseId());
        return optionalWarehouse.filter(warehouse -> warehouse.isEnoughSpace() == warehouseCommand.isEnoughSpace());
    }
}
