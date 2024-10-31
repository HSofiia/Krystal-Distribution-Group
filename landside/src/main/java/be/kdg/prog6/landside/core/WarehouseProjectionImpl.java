package be.kdg.prog6.landside.core;

import be.kdg.prog6.common.domain.ActivityAmountType;
import be.kdg.prog6.landside.domain.WarehouseProjector;
import be.kdg.prog6.landside.port.in.WarehouseProjection;
import be.kdg.prog6.landside.port.out.warehouse.LoadWarehousePort;
import be.kdg.prog6.landside.port.out.warehouse.UpdatedWarehousePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class WarehouseProjectionImpl implements WarehouseProjection {

    private final LoadWarehousePort loadWarehousePort;
    private final UpdatedWarehousePort updatedWarehousePort;

    public WarehouseProjectionImpl(LoadWarehousePort loadWarehousePort, UpdatedWarehousePort updatedWarehousePort) {
        this.loadWarehousePort = loadWarehousePort;
        this.updatedWarehousePort = updatedWarehousePort;
    }

    @Override
    @Transactional
    public void warehouseProjection(int warehouseNumber, ActivityAmountType type, double amount) {
        WarehouseProjector warehouseProjector = loadWarehousePort.loadWarehouseByNumber(warehouseNumber).orElseThrow();
        warehouseProjector.modifyCapacity(type, amount);
        updatedWarehousePort.updateWarehouse(warehouseProjector);
    }
}
