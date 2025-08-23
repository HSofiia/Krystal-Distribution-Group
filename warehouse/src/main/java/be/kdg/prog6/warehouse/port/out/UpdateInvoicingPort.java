package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.common.event.ChangePayloadStorageEvent;

public interface UpdateInvoicingPort {
    void send(ChangePayloadStorageEvent changePayloadStorageEvent);
}
