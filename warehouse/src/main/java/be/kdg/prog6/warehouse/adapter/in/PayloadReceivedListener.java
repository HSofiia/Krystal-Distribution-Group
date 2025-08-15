package be.kdg.prog6.warehouse.adapter.in;

import be.kdg.prog6.common.event.ConveyorPayloadEvent;
import be.kdg.prog6.warehouse.port.in.PayloadCommand;
import be.kdg.prog6.warehouse.port.in.PayloadManagementUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PayloadReceivedListener {

    public static final String PAYLOAD_DELIVERY_TICKET_QUEUE = "payload_delivery_ticket_queue";
    private final PayloadManagementUseCase payloadManagementUseCase;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public PayloadReceivedListener(PayloadManagementUseCase payloadManagementUseCase) {
        this.payloadManagementUseCase = payloadManagementUseCase;
    }

    @RabbitListener(queues = PAYLOAD_DELIVERY_TICKET_QUEUE, messageConverter = "jackson2JsonMessageConverter")
    public void payloadDelivered(ConveyorPayloadEvent pdtReceivedEvent) {
        logger.info(
                "Payload of %s delivered to warehouse %s"
                        .formatted(
                            pdtReceivedEvent.materialType(),
                            pdtReceivedEvent.warehouseNumber()
                )
        );
        payloadManagementUseCase.savePayload(
                new PayloadCommand(
                        pdtReceivedEvent.warehouseNumber(),
                        pdtReceivedEvent.time(),
                        pdtReceivedEvent.netWeight()));
    }
}
