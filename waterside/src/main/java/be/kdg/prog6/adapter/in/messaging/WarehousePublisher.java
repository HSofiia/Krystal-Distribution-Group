package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.domain.PONumber;
import be.kdg.prog6.port.out.MatchSOandPOPort;
import be.kdg.prog6.port.out.SOCompletedPort;
import be.kdg.prog6.common.event.ChangePOStatusEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class WarehousePublisher implements SOCompletedPort, MatchSOandPOPort {

    private final RabbitTemplate rabbitTemplate;

    public WarehousePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void deductMaterialFromWarehouse(PONumber poNumber) {
        String routingKey = "status.%s.issued".formatted(poNumber.number());
        this.rabbitTemplate.convertAndSend(
                MQTopology.CHANGE_ORDER_STATUS_EXCHANGE,
                routingKey,
                new ChangePOStatusEvent(poNumber.number())
        );
    }

    @Override
    public void matchSOandPO(PONumber poNumber) {
        String routingKey = "status.%s.matched".formatted(poNumber.number());
        this.rabbitTemplate.convertAndSend(
                MQTopology.CHANGE_ORDER_STATUS_EXCHANGE,
                routingKey,
                new ChangePOStatusEvent(poNumber.number())
        );
    }
}
