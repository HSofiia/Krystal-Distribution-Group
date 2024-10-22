package be.kdg.prog6.warehouse.adapter.in;

import be.kdg.prog6.common.event.ConveyorPayloadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DumpPayloadEventListener {

    private static final Logger log = LoggerFactory.getLogger(DumpPayloadEventListener.class);

    public static final String PAYLOAD_RECEIVED_QUEUE = "payload_received";

    @RabbitListener(queues = PAYLOAD_RECEIVED_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void payloadReceived(final ConveyorPayloadEvent event) {
        log.info(
                "payload info received {}, {}, {}, {}",
                event.licencePlate(),
                event.time(),
                event.materialType(),
                event.warehouseNumber()
        );
    }
}
