package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.common.domain.ActivityType;
import be.kdg.prog6.common.event.ChangePayloadStorageEvent;
import be.kdg.prog6.common.event.ChangeWarehouseCapacityEvent;
import be.kdg.prog6.warehouse.domain.ActivityId;
import be.kdg.prog6.warehouse.domain.PayloadActivity;
import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.port.in.PayloadCommand;
import be.kdg.prog6.warehouse.port.in.PayloadManagementUseCase;
import be.kdg.prog6.warehouse.port.out.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PayloadManagementUseCaseImpl implements PayloadManagementUseCase {

    private final LoadWarehousePort warehousePort;
    private final UpdatePayloadActivityPort updatePayloadPort;
    private final SavePayloadActivityPort savePayloadPort;
    private final WarehouseProjectionPort warehouseProjection;
    private final UpdateWarehouseCapacityPort updateWarehouseCapacityPort;
    private final UpdateInvoicingPort updateInvoicingPort;
    private final Logger logger = LoggerFactory.getLogger(PayloadManagementUseCaseImpl.class);


    public PayloadManagementUseCaseImpl(LoadWarehousePort warehousePort, UpdatePayloadActivityPort updatePayloadPort, SavePayloadActivityPort savePayloadPort, WarehouseProjectionPort warehouseProjection, UpdateWarehouseCapacityPort updateWarehouseCapacityPort, UpdateInvoicingPort updateInvoicingPort) {
        this.warehousePort = warehousePort;
        this.updatePayloadPort = updatePayloadPort;
        this.savePayloadPort = savePayloadPort;
        this.warehouseProjection = warehouseProjection;
        this.updateWarehouseCapacityPort = updateWarehouseCapacityPort;
        this.updateInvoicingPort = updateInvoicingPort;
    }

    @Override
    @Transactional
    public void savePayload(PayloadCommand payloadCommand) {
        Warehouse warehouse = warehousePort.loadWarehouseByNumberSnapshot(payloadCommand.warehouseNumber());

        Optional<PayloadActivity> pendingActivity = warehouse.isPendingPayloadActivity();

        if (pendingActivity.isPresent()) {
            PayloadActivity existingActivity = pendingActivity.get();

            updatePayloadPort.updatePayload(
                    existingActivity,
                    warehouse.getWarehouseNumber(),
                    payloadCommand.netWeight()
            );
        } else {
            PayloadActivity newActivity = warehouse.createDeliveryActivity(payloadCommand.netWeight(), payloadCommand.time());

            savePayloadPort.savePayload(newActivity, payloadCommand.warehouseNumber());
        }

        updateInvoicingPort.send(
                new ChangePayloadStorageEvent(
                        UUID.randomUUID(),
                        warehouse.getSeller().getSellerId().sellerId(),
                        warehouse.getMaterialType().name(),
                        payloadCommand.netWeight(),
                        payloadCommand.time()
                ));

        updateWarehouseCapacityPort.updateWarehouse(warehouse);

        ChangeWarehouseCapacityEvent capacityChange =
                new ChangeWarehouseCapacityEvent(
                        warehouse.getWarehouseNumber(),
                        ActivityType.DELIVERY,
                        payloadCommand.netWeight()
                );

        warehouseProjection.warehouseCapacityProjection(capacityChange);

        logger.info("%.2f tons of payload recorded for warehouse %s".formatted(
                payloadCommand.netWeight(),
                warehouse.getWarehouseNumber())
        );
    }
}
