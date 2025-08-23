package be.kdg.prog6.core.core;

import be.kdg.prog6.domain.BunkeringOperation;
import be.kdg.prog6.domain.OperationStatus;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.port.in.PlanBunkeringOperationUseCase;
import be.kdg.prog6.port.out.FindSOPort;
import be.kdg.prog6.port.out.SOCompletedPort;
import be.kdg.prog6.port.out.UpdateSOPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PlanBunkeringOperationUseCaseImpl implements PlanBunkeringOperationUseCase {

    private static final Logger log = LoggerFactory.getLogger(PlanBunkeringOperationUseCaseImpl.class);
    private final FindSOPort findSOPort;
    private final UpdateSOPort updateSOPort;
    private final SOCompletedPort soCompletedPort;

    public PlanBunkeringOperationUseCaseImpl(FindSOPort findSOPort, UpdateSOPort updateSOPort, SOCompletedPort soCompletedPort) {
        this.findSOPort = findSOPort;
        this.updateSOPort = updateSOPort;
        this.soCompletedPort = soCompletedPort;
    }

    @Override
    @Transactional
    public void planBO(String vesselNumber, LocalDate date) {
        if (findSOPort.findAllShipmentOrderByBunkeringOperationDate(date).size() == BunkeringOperation.MAX_BO_LIMIT) {
            throw new RuntimeException("Limit exceeded for bunkering");
        }
        ShipmentOrder shipmentOrder = findSOPort.getByVesselNumberAndNotStatus(vesselNumber, OperationStatus.COMPLETED);

        shipmentOrder.scheduleBO(date);
        updateSOPort.updateShipmentOrder(shipmentOrder);

        log.info("Bunkering for vessel {} scheduled {} ", vesselNumber, date);

        if (shipmentOrder.readyToDepart()) {
            shipmentOrder.depart();
            shipmentOrder = updateSOPort.updateShipmentOrder(shipmentOrder);
            soCompletedPort.deductMaterialFromWarehouse(shipmentOrder.getPoNumber());
            log.info("Ship %s left site".formatted(shipmentOrder.getVesselNumber()));
        }
    }
}

