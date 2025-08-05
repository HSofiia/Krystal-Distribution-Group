package be.kdg.prog6.landside.port.out;

import be.kdg.prog6.common.event.ConveyorPayloadEvent;

public interface CreatePdtPort {
    void sendPdt(ConveyorPayloadEvent payloadDeliveredEvent);
}
