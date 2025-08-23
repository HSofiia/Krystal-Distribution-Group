package be.kdg.prog4.core;

import be.kdg.prog4.domain.*;
import be.kdg.prog4.event.CommissionEvent;
import be.kdg.prog4.port.in.CalculateCommissionFeeUseCase;
import be.kdg.prog4.port.out.*;
import be.kdg.prog6.common.domain.MaterialType;
import be.kdg.prog6.common.domain.SellerId;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CalculateCommissionFeeUseCaseImpl implements CalculateCommissionFeeUseCase {
    private final SaveCommissionFeePort saveCommissionFeePort;
    private final LoadCommissionFeePort loadCommissionFeePort;
    private final LoadWarehousePort loadWarehousePort;
    private final UpdateStoragePort updateStoragePort;
    private static final Logger log = LoggerFactory.getLogger(CalculateCommissionFeeUseCaseImpl.class);

    public CalculateCommissionFeeUseCaseImpl(SaveCommissionFeePort saveCommissionFeePort, LoadCommissionFeePort loadCommissionFeePort, LoadWarehousePort loadWarehousePort, UpdateStoragePort updateStoragePort) {
        this.saveCommissionFeePort = saveCommissionFeePort;
        this.loadCommissionFeePort = loadCommissionFeePort;
        this.loadWarehousePort = loadWarehousePort;
        this.updateStoragePort = updateStoragePort;
    }

    @Override
    @Transactional
    public void registerCommission(CommissionEvent event) {
        Optional<CommissionFee> existing = loadCommissionFeePort.loadByPoNumber(new PONumber(event.poNumber()));
        if (existing.isPresent()) {
            log.info("Commission already exists for PO {} – skipping", event.poNumber());
            return;
        }

        double totalFee = event.orderLines().stream()
                .mapToDouble(line -> {
                    double tons = line.toTons();
                    Warehouse warehouse = loadWarehousePort.loadBySellerIdAndMaterialType(new SellerId(event.sellerId()), MaterialType.valueOf(line.materialType()));
                    warehouse.removeOldPayloadsFirst(tons);
                    updateStoragePort.update(warehouse);
                    return warehouse.calculateCommissionFee(tons);
                })
                .sum();

        CommissionFee fee = new CommissionFee(UUID.randomUUID(), new SellerId(event.sellerId()), new PONumber(event.poNumber()), totalFee);
        saveCommissionFeePort.save(fee);

        log.info("Commission fee {} recorded for PO {}", totalFee, event.poNumber());
    }
}
