package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.common.domain.ActivityType;
import be.kdg.prog6.common.event.ChangeWarehouseCapacityEvent;
import be.kdg.prog6.warehouse.domain.*;
import be.kdg.prog6.warehouse.events.CalculatePOCommissionEvent;
import be.kdg.prog6.warehouse.port.in.WarehouseIssueMaterialsUseCase;
import be.kdg.prog6.warehouse.port.out.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class WarehouseIssueMaterialsUseCaseImpl implements WarehouseIssueMaterialsUseCase {

    private static final Logger log = LoggerFactory.getLogger(WarehouseIssueMaterialsUseCaseImpl.class);

    private final LoadWarehousePort loadWarehousePort;
    private final LoadPOPort loadPOPort;
    private final UpdatePOPort updatePOPort;
    private final WarehouseProjectionPort warehouseProjectionPort;
    private final SavePayloadActivityPort savePayloadActivity;
    private final InvoicingPOPort invoicingPOPort;

    public WarehouseIssueMaterialsUseCaseImpl(LoadWarehousePort loadWarehousePort, LoadPOPort loadPOPort, UpdatePOPort updatePOPort, WarehouseProjectionPort warehouseProjectionPort, SavePayloadActivityPort savePayloadActivity, InvoicingPOPort invoicingPOPort) {
        this.loadWarehousePort = loadWarehousePort;
        this.loadPOPort = loadPOPort;
        this.updatePOPort = updatePOPort;
        this.warehouseProjectionPort = warehouseProjectionPort;
        this.savePayloadActivity = savePayloadActivity;
        this.invoicingPOPort = invoicingPOPort;
    }

    @Override
    @Transactional
    public void issueMaterialsAmount(PONumber poNumber) {
        PO po = loadPOPort.getByPurchaseOrderNumber(poNumber.number());

        if (!po.isNotFilled()) {
            log.info("PO {} already fulfilled — skipping", poNumber.number());
            return;
        }

        // For every order line: convert to tons, remove oldest payloads first, persist an activity, and update capacity
        for (OrderLine line : po.orderLines()) {
            double tons = line.getAmount();
            Warehouse wh = loadWarehousePort.loadWarehouseByOwnerIdAndMaterialType(po.getSeller().getSellerId(), line.materialType());

            // record a PURCHASE activity using the single PayloadActivity class
            PayloadActivity activity = new PayloadActivity(
                    new ActivityId(wh.getWarehouseNumber(), UUID.randomUUID()),
                    tons,
                    LocalDateTime.now(),
                    ActivityType.PURCHASE
            );
            savePayloadActivity.savePayload(activity, wh.getWarehouseNumber());

            ChangeWarehouseCapacityEvent warehouseCapacityEvent = new ChangeWarehouseCapacityEvent(
                    wh.getWarehouseNumber(),
                    ActivityType.PURCHASE,
                    tons
            );
            warehouseProjectionPort.warehouseCapacityProjection(warehouseCapacityEvent);

            // send to your projection port if you have one (not shown here)
            log.info("Issued {} tons of {} from warehouse {}", tons, line.materialType(), wh.getWarehouseNumber());
        }

        // mark PO as fulfilled and persist
        po.fillOrder();
        updatePOPort.update(po);

        // notify invoicing to calculate commission for this PO
        CalculatePOCommissionEvent commissionEvent = new CalculatePOCommissionEvent(
                po.orderLines().stream()
                        .map(ol -> new CalculatePOCommissionEvent.CommissionOrderLine(
                                ol.materialType().name(),
                                ol.quantity(),
                                ol.measure().getCode()
                        ))
                        .toList(),
                po.getSeller().getSellerId().sellerId(),
                po.poNumber().number()
        );

        log.info("Publishing commission request for PO {}", po.poNumber().number());
        invoicingPOPort.publishCommissionRequest(commissionEvent);

        log.info("PO {} fulfilled and invoicing notified", poNumber.number());
    }
}
