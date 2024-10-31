package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.warehouse.port.in.PayloadCommand;

public interface SaveWarehousePayloadRecordPort {
    void saveOrUpdatePDT(PayloadCommand command);
}
