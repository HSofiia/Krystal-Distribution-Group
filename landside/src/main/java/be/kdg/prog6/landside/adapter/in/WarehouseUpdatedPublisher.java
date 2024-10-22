//package be.kdg.prog6.landside.adapter.in;
//
//import be.kdg.prog6.landside.domain.Warehouse;
//import be.kdg.prog6.landside.port.out.warehouse.UpdatedWarehousePort;
//import org.slf4j.Logger;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//import static org.slf4j.LoggerFactory.getLogger;
//
//
//@Component
//public class WarehouseUpdatedPublisher implements UpdatedWarehousePort {
//    private final Logger log = getLogger(WarehouseUpdatedPublisher.class);
//    private final RabbitTemplate rabbitTemplate;
//
//    public WarehouseUpdatedPublisher(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @Override
//    public void updateWarehouse(final Warehouse warehouse) {
//        log.info("Publishing message about updated warehouse.");
//
//        WarehouseUpdatedEvent warehouseUpdatedEvent = new WarehouseUpdatedEvent(
//                warehouse.warehouseId(),
//                warehouse.isEnoughSpace()
//        );
//
//        String routingKey = "warehouse.%s.status.updated".formatted(warehouse.warehouseId());
//
//        this.rabbitTemplate.convertAndSend(
//                RabbitMQTopology.WAREHOUSE_EVENTS_EXCHANGE,
//                routingKey,
//                warehouseUpdatedEvent
//        );
//    }
//}
