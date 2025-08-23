package be.kdg.prog6.core.core;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.RegisterShipArrivalUseCase;
import be.kdg.prog6.port.out.SaveSOPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegisterShipArrivalUseCaseImpl implements RegisterShipArrivalUseCase {

    private static final Logger log = LoggerFactory.getLogger(RegisterShipArrivalUseCaseImpl.class);
    private final SaveSOPort saveSOPort;

    public RegisterShipArrivalUseCaseImpl(SaveSOPort saveSOPort) {
        this.saveSOPort = saveSOPort;
    }

    @Override
    @Transactional
    public void inputInformation(String poRefernceNumber, String vesselNumber, String customerEnterpriseNumber) {
        ShipmentOrder shipmentOrder = new ShipmentOrder(
                new PONumber(poRefernceNumber),
                customerEnterpriseNumber,
                vesselNumber
        );
        saveSOPort.saveSO(shipmentOrder);
        log.info("Ship {} arrived", shipmentOrder.getVesselNumber());
    }
}
