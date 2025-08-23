package be.kdg.prog6.core.core;

import be.kdg.prog6.domain.OperationStatus;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.port.in.CompleteInspectionUseCase;
import be.kdg.prog6.port.in.InspectionCommand;
import be.kdg.prog6.port.out.FindSOPort;
import be.kdg.prog6.port.out.SOCompletedPort;
import be.kdg.prog6.port.out.UpdateSOPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CompleteInspectionUseCaseImpl implements CompleteInspectionUseCase {

    private static final Logger log = LoggerFactory.getLogger(CompleteInspectionUseCaseImpl.class);
    private final UpdateSOPort updateSOPort;
    private final FindSOPort findSOPort;
    private final SOCompletedPort soCompletedPort;


    public CompleteInspectionUseCaseImpl(UpdateSOPort updateSOPort, FindSOPort findSOPort, SOCompletedPort soCompletedPort) {
        this.updateSOPort = updateSOPort;
        this.findSOPort = findSOPort;
        this.soCompletedPort = soCompletedPort;
    }

    @Override
    @Transactional
    public void completeInspection(InspectionCommand command) {
        ShipmentOrder shipmentOrder = findSOPort.getByVesselNumberAndNotStatus(command.vesselNumber(), OperationStatus.COMPLETED);
        shipmentOrder.completeIO(command.inspectionDate(), command.inspectorSignature());

        updateSOPort.updateShipmentOrder(shipmentOrder);
        log.info("Inspected {} by {} on {}",
                command.vesselNumber(),
                command.inspectorSignature(),
                command.inspectionDate());

        if (shipmentOrder.readyToDepart()) {
            shipmentOrder.depart();
            shipmentOrder = updateSOPort.updateShipmentOrder(shipmentOrder);
            soCompletedPort.deductMaterialFromWarehouse(shipmentOrder.getPoNumber());
            log.info("Ship %s left site".formatted(shipmentOrder.getVesselNumber()));
        }
    }
}
