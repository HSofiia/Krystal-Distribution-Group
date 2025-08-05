package be.kdg.prog6.landside.adapter.in.messaging;

import be.kdg.prog6.common.event.ChangeWarehouseCapacityEvent;
import be.kdg.prog6.landside.port.in.WarehouseProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CapacityEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CapacityEventListener.class);
    public static final String WAREHOUSE_CAPACITY_QUEUED = "warehouse_capacity_queue";

    private final WarehouseProjection warehouseProjection;

    public CapacityEventListener(WarehouseProjection warehouseProjection) {
        this.warehouseProjection = warehouseProjection;
    }

    @RabbitListener(queues = WAREHOUSE_CAPACITY_QUEUED)
    public void capacityEvent(final ChangeWarehouseCapacityEvent capacityEvent){
        if (capacityEvent.type() == null) {
            LOGGER.warn("Received null ActivityAmountType, skipping capacity modification.");
            return;
        }
        LOGGER.info(
                "{} activity got created on warehouse of {} with amount of {}",
                capacityEvent.warehouseNumber(),
                capacityEvent.type(),
                capacityEvent.amount()
        );
        warehouseProjection.warehouseProjection(
                capacityEvent.warehouseNumber(),
                capacityEvent.type(),
                capacityEvent.amount()
        );
    }
}
