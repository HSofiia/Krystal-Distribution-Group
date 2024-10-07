package be.kdg.prog6.family.adapter.in;

import be.kdg.prog6.family.port.in.WarehouseProjection;
import be.kdg.prog6.family.port.in.WarehouseProjectionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class WarehouseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseController.class);
    private final WarehouseProjection warehouseUpdateProjection;

    public WarehouseController(WarehouseProjection warehouseUpdateProjection) {
        this.warehouseUpdateProjection = warehouseUpdateProjection;
    }

    @RabbitListener(queues = "warehouse_status", messageConverter = "#{jackson2JsonMessageConverter}")
    void warehouseStatusUpdatedListener(WarehouseUpdatedEvent warehouseUpdatedEvent) {
        WarehouseProjectionCommand command = new WarehouseProjectionCommand(
                warehouseUpdatedEvent.warehouseId(),
                warehouseUpdatedEvent.isEnoughSpace()
        );

        LOGGER.info("The warehouse with id {} has enough space: {}",
                warehouseUpdatedEvent.warehouseId(),
                warehouseUpdatedEvent.isEnoughSpace());

        warehouseUpdateProjection.warehouseProjection(command);
    }
}
