package be.kdg.prog6.warehouse.core;

import be.kdg.prog6.common.domain.ActivityAmountType;
import be.kdg.prog6.warehouse.port.in.PayloadCommand;
import be.kdg.prog6.warehouse.port.in.PayloadManagementUseCase;
import be.kdg.prog6.warehouse.port.out.SaveWarehousePayloadRecordPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PayloadManagementUseCaseImpl implements PayloadManagementUseCase {

    private final SaveWarehousePayloadRecordPort saveWarehousePayloadRecordPort;

    public PayloadManagementUseCaseImpl(SaveWarehousePayloadRecordPort saveWarehousePayloadRecordPort) {
        this.saveWarehousePayloadRecordPort = saveWarehousePayloadRecordPort;
    }

    @Override
    @Transactional
    public void savePayload(int warehouseNumber, LocalDateTime time, double netWeight) {
        saveWarehousePayloadRecordPort.saveOrUpdatePDT(new PayloadCommand(
                warehouseNumber,
                time,
                netWeight,
                ActivityAmountType.DELIVERY
                ));

    }
}
