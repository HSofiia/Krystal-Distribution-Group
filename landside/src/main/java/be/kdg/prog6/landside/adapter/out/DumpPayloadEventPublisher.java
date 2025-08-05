//package be.kdg.prog6.landside.adapter.out;
//
//import be.kdg.prog6.common.domain.MaterialType;
//import be.kdg.prog6.common.domain.TruckPlate;
//import be.kdg.prog6.common.event.ConveyorPayloadEvent;
//import be.kdg.prog6.landside.adapter.in.messaging.RabbitMQTopology;
//import be.kdg.prog6.landside.port.out.ConveyorPayloadPort;
//import org.slf4j.Logger;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//import static org.slf4j.LoggerFactory.getLogger;
//
//@Component
//public class DumpPayloadEventPublisher implements ConveyorPayloadPort {
//
//    private final Logger log = getLogger(DumpPayloadEventPublisher.class);
//
//    private final RabbitTemplate rabbitTemplate;
//
//    public DumpPayloadEventPublisher(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @Override
//    public void conveyorPayload(MaterialType materialType, int warehouseNumber, TruckPlate licencePlate, LocalDateTime time, double netWeight) {
//        log.info("publishing message about warehouse payload");
//
//        ConveyorPayloadEvent conveyorPayloadEvent = new ConveyorPayloadEvent(materialType, warehouseNumber, licencePlate, time, netWeight);
//        String routingKey = "warehouse.%s.weight.received".formatted(warehouseNumber);
//
//        this.rabbitTemplate.convertAndSend(
//                RabbitMQTopology.WAREHOUSE_EVENTS_EXCHANGE,
//                routingKey,
//                conveyorPayloadEvent
//        );
//    }
//}
