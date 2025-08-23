package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.warehouse.domain.PO;
import be.kdg.prog6.warehouse.domain.PONumber;
import be.kdg.prog6.warehouse.port.in.MatchSOAndPOUseCase;
import be.kdg.prog6.warehouse.port.out.LoadPOPort;
import be.kdg.prog6.warehouse.port.out.UpdatePOPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MatchSOAndPOUseCaseImpl implements MatchSOAndPOUseCase {

    private final LoadPOPort loadPOPort;
    private final UpdatePOPort updatePOPort;

    public MatchSOAndPOUseCaseImpl(LoadPOPort loadPOPort, UpdatePOPort updatePOPort) {
        this.loadPOPort = loadPOPort;
        this.updatePOPort = updatePOPort;
    }

    @Override
    @Transactional
    public void matchSOandPO(PONumber poNumber) {
        PO purchaseOrder = loadPOPort.getByPurchaseOrderNumber(poNumber.number());
        if (purchaseOrder.isOutstanding()) {
            purchaseOrder.matchOrder();
            updatePOPort.update(purchaseOrder);
        }

    }
}
