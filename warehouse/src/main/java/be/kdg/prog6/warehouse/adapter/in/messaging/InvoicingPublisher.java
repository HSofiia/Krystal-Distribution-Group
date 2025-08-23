package be.kdg.prog6.warehouse.adapter.in.messaging;

import be.kdg.prog6.common.event.ChangePayloadStorageEvent;
import be.kdg.prog6.warehouse.events.CalculatePOCommissionEvent;
import be.kdg.prog6.warehouse.port.out.InvoicingPOPort;
import be.kdg.prog6.warehouse.port.out.UpdateInvoicingPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InvoicingPublisher implements InvoicingPOPort, UpdateInvoicingPort {
    private final RabbitTemplate rabbitTemplate;

    public InvoicingPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishCommissionRequest(CalculatePOCommissionEvent event){
        String routingKey = "po.%s.commission.request".formatted(UUID.randomUUID());
        rabbitTemplate.convertAndSend(
                RabbitMQTopology.INVOICING_EXCHANGE,
                routingKey,
                event
        );
    }

    @Override
    public void send(ChangePayloadStorageEvent changePayloadStorageEvent){
        String routingKey = "payload.%s.delivered".formatted(UUID.randomUUID());
        this.rabbitTemplate.convertAndSend(
                RabbitMQTopology.INVOICING_EXCHANGE,
                routingKey,
                changePayloadStorageEvent);
    }
}
