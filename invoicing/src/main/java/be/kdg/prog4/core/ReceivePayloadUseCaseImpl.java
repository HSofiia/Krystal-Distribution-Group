package be.kdg.prog4.core;

import be.kdg.prog4.domain.Warehouse;
import be.kdg.prog4.port.in.ReceivePayloadUseCase;
import be.kdg.prog4.port.out.LoadWarehousePort;
import be.kdg.prog4.port.out.UpdateStoragePort;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import be.kdg.prog6.common.event.ChangePayloadStorageEvent;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReceivePayloadUseCaseImpl implements ReceivePayloadUseCase {
    private final UpdateStoragePort updateStoragePort;
    private final LoadWarehousePort loadWarehousePort;
    private static final Logger log = LoggerFactory.getLogger(ReceivePayloadUseCaseImpl.class);

    public ReceivePayloadUseCaseImpl(UpdateStoragePort updateStoragePort, LoadWarehousePort loadWarehousePort) {
        this.updateStoragePort = updateStoragePort;
        this.loadWarehousePort = loadWarehousePort;
    }

    @Override
    @Transactional
    public void receive(ChangePayloadStorageEvent changePayloadStorageEvent) {
        Warehouse warehouse = loadWarehousePort.loadBySellerIdAndMaterialType(new SellerId(changePayloadStorageEvent.sellerId()), MaterialType.valueOf(changePayloadStorageEvent.materialType()));
        warehouse.addPayload(changePayloadStorageEvent.tons(), changePayloadStorageEvent.deliveredAt());

        updateStoragePort.update(warehouse);
        log.info("Warehouse received %.2f tons of %s materials".formatted(changePayloadStorageEvent.tons(), changePayloadStorageEvent.materialType()));
    }
}
