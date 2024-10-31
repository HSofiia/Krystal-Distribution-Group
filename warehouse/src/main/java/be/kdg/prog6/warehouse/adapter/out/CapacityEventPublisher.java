package be.kdg.prog6.warehouse.adapter.out;

import be.kdg.prog6.common.event.ChangeWarehouseCapacityEvent;
import be.kdg.prog6.warehouse.domain.PayloadActivity;
import be.kdg.prog6.warehouse.domain.Warehouse;
import be.kdg.prog6.warehouse.port.out.UpdateWarehouseCapacityPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CapacityEventPublisher implements UpdateWarehouseCapacityPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(CapacityEventPublisher.class);

    private static final String EXCHANGE_NAME = "landside_events";

    private final RabbitTemplate rabbitTemplate;

    public CapacityEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void activityAdded(Warehouse warehouse, PayloadActivity activity) {
        final String routingKey = "warehouse." + warehouse.getWarehouseNumber() + ".activity.created";

        LOGGER.info("Notifying RabbitMQ: {}", routingKey);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, new ChangeWarehouseCapacityEvent(
                warehouse.getWarehouseNumber(),
                activity.activityType(),
                activity.amount()
        ));
    }
}
