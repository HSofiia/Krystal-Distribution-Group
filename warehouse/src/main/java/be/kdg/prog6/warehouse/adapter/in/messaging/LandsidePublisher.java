package be.kdg.prog6.warehouse.adapter.in.messaging;

import be.kdg.prog6.common.event.ChangeWarehouseCapacityEvent;
import be.kdg.prog6.warehouse.port.out.WarehouseProjectionPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LandsidePublisher implements WarehouseProjectionPort {

    private final RabbitTemplate rabbitTemplate;

    public LandsidePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void warehouseCapacityProjection(ChangeWarehouseCapacityEvent event) {
        String routingKey = String.format("warehouse.%s.capacity.updated", UUID.randomUUID());
        this.rabbitTemplate.convertAndSend(
                RabbitMQTopology.LANDSIDE_EXCHANGE,
                routingKey,
                event
        );
    }
}
