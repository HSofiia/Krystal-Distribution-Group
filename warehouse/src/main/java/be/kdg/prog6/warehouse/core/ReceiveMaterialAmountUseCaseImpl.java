package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.port.in.ReceiveMaterialAmountCommand;
import be.kdg.prog6.warehouse.port.in.ReceiveMaterialAmountUseCase;
import be.kdg.prog6.warehouse.port.out.LoadWarehousePort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceiveMaterialAmountUseCaseImpl implements ReceiveMaterialAmountUseCase {

    private final LoadWarehousePort loadWarehousePort;

    public ReceiveMaterialAmountUseCaseImpl(LoadWarehousePort loadWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    public Optional<Warehouse> receiveMaterial(ReceiveMaterialAmountCommand receiveMaterialCommand) {
        Warehouse warehouse = loadWarehousePort.loadWarehouseById(receiveMaterialCommand.warehouseId());
    }
}
