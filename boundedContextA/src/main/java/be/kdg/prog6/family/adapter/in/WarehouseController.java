package be.kdg.prog6.family.adapter.in;

import be.kdg.prog6.family.domain.MaterialType;
import be.kdg.prog6.family.domain.SellerId;
import be.kdg.prog6.family.domain.TruckPlate;
import be.kdg.prog6.family.port.in.MakeAppointmentCommand;
import be.kdg.prog6.family.port.in.WarehouseProjection;
import be.kdg.prog6.family.port.in.WarehouseProjectionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class WarehouseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseController.class);
    private final WarehouseProjection warehouseProjection;

    public WarehouseController(WarehouseProjection warehouseProjection) {
        this.warehouseProjection = warehouseProjection;
    }

    @RabbitListener(queues = "events", messageConverter = "#{jackson2JsonMessageConverter}")
    void warehouseUpdatedListener(WarehouseUpdatedEvent warehouseUpdatedEvent){

        WarehouseProjectionCommand command = new WarehouseProjectionCommand(
                warehouseUpdatedEvent.warehouseId(),
                warehouseUpdatedEvent.isEnoughSpace()
        );

        LOGGER.info("The warehouse with id {} is full up: {}",
                warehouseUpdatedEvent.warehouseId(),
                warehouseUpdatedEvent.isEnoughSpace());
        warehouseProjection.warehouseProjection(command);
    }

}
