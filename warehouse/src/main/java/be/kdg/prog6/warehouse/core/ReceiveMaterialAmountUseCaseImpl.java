package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.domain.PayloadActivity;
import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.domain.WarehouseCurrentCapacity;
import be.kdg.prog6.warehouse.domain.WarehouseReceive;
import be.kdg.prog6.warehouse.port.in.ReceiveMaterialAmountCommand;
import be.kdg.prog6.warehouse.port.in.ReceiveMaterialAmountUseCase;
import be.kdg.prog6.warehouse.port.out.LoadWarehousePort;
import be.kdg.prog6.warehouse.port.out.SaveWarehousePayloadRecordPort;
import be.kdg.prog6.warehouse.port.out.UpdateWarehouseCapacityPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiveMaterialAmountUseCaseImpl implements ReceiveMaterialAmountUseCase {

    private final LoadWarehousePort loadWarehousePort;
    private final List<UpdateWarehouseCapacityPort> updateWarehouseCapacityPorts;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveMaterialAmountUseCaseImpl.class);



    public ReceiveMaterialAmountUseCaseImpl(LoadWarehousePort loadWarehousePort, List<UpdateWarehouseCapacityPort> updateWarehouseCapacityPorts) {
        this.loadWarehousePort = loadWarehousePort;
        this.updateWarehouseCapacityPorts = updateWarehouseCapacityPorts;
    }

    @Override
    @Transactional
    public WarehouseCurrentCapacity receiveMaterial(ReceiveMaterialAmountCommand receiveMaterialCommand) {
        Optional<Warehouse> warehouseOpt = loadWarehousePort.loadWarehouseByNumber(receiveMaterialCommand.warehouseNumber());
        Warehouse warehouse = warehouseOpt.get();
        final PayloadActivity activity = warehouse.putMaterial(receiveMaterialCommand.amountReceived());
        updateWarehouseCapacityPorts.forEach(port -> port.activityAdded(warehouse, activity));
        LOGGER.info("Received {}", activity);
        return warehouse.calculateCapacity();
    }
}
