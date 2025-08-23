package be.kdg.prog6.core.core;

import be.kdg.prog6.domain.OperationStatus;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.port.in.MatchSOAndPOUseCase;
import be.kdg.prog6.port.out.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchSOAndPOUseCaseImpl implements MatchSOAndPOUseCase {

    private static final Logger log = LoggerFactory.getLogger(MatchSOAndPOUseCaseImpl.class);
    private final FindSOPort findSOPort;
    private final UpdateSOPort updateSOPort;
    private final MatchSOandPOPort matchSOandPOPort;
    private final SOCompletedPort soCompletedPort;

    public MatchSOAndPOUseCaseImpl(FindSOPort findSOPort, UpdateSOPort updateSOPort, MatchSOandPOPort matchSOandPOPort, SOCompletedPort soCompletedPort) {
        this.findSOPort = findSOPort;
        this.updateSOPort = updateSOPort;
        this.matchSOandPOPort = matchSOandPOPort;
        this.soCompletedPort = soCompletedPort;
    }

    @Override
    @Transactional
    public void matchSOAndPO(String vesselNumber) {
        ShipmentOrder shipmentOrder = findSOPort.getByVesselNumberAndNotStatus(vesselNumber, OperationStatus.COMPLETED);

        shipmentOrder.matchPurchaseOrder();
        updateSOPort.updateShipmentOrder(shipmentOrder);
        matchSOandPOPort.matchSOandPO(shipmentOrder.getPoNumber());
        log.info("Matched SO and PO with vessel number {}", vesselNumber);

        if (shipmentOrder.readyToDepart()) {
            shipmentOrder.depart();
            shipmentOrder = updateSOPort.updateShipmentOrder(shipmentOrder);
            soCompletedPort.deductMaterialFromWarehouse(shipmentOrder.getPoNumber());
            log.info("Ship %s left site".formatted(shipmentOrder.getVesselNumber()));
        }
    }
}
