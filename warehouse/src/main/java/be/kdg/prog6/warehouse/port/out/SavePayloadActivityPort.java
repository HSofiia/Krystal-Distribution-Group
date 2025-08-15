package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.warehouse.domain.PayloadActivity;

public interface SavePayloadActivityPort {
    void savePayload(PayloadActivity payloadActivity, int warehouseNumber);
}
