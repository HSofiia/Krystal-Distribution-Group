package be.kdg.prog6.warehouse.port.out;

import be.kdg.prog6.warehouse.domain.PayloadActivity;

public interface UpdatePayloadActivityPort {
    void updatePayload(PayloadActivity payloadActivity, int warehouseNumber, double netWeight);
}
