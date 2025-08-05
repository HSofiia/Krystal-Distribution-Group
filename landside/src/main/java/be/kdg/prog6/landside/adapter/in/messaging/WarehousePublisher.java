package be.kdg.prog6.landside.adapter.in.messaging;

import be.kdg.prog6.common.event.ConveyorPayloadEvent;
import be.kdg.prog6.landside.port.out.CreatePdtPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WarehousePublisher implements CreatePdtPort {

    private final RabbitTemplate rabbitTemplate;

    public WarehousePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendPdt(ConveyorPayloadEvent pdt) {
        String routingKey = String.format("landside.%s.pdt.received", UUID.randomUUID());
        this.rabbitTemplate.convertAndSend(
                RabbitMQTopology.PAYLOAD_TICKET_EXCHANGE,
                routingKey,
                pdt
        );
    }
}
