package be.kdg.prog4.adapter.in.messaging;

import be.kdg.prog4.event.CommissionEvent;
import be.kdg.prog4.port.in.CalculateCommissionFeeUseCase;
import be.kdg.prog4.port.in.ReceivePayloadUseCase;
import be.kdg.prog6.common.event.ChangePayloadStorageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CommissionRequestListener {

    private final CalculateCommissionFeeUseCase useCase;
    private final ReceivePayloadUseCase receivePayloadDelivery;
    private static final Logger log = LoggerFactory.getLogger(CommissionRequestListener.class);

    public CommissionRequestListener(CalculateCommissionFeeUseCase useCase, ReceivePayloadUseCase receivePayloadDelivery) {
        this.useCase = useCase;
        this.receivePayloadDelivery = receivePayloadDelivery;
    }

    @RabbitListener(queues = MQTopology.COMMISSIONS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void onCommissionRequest(CommissionEvent event) {
        log.info("Received commission event");
        useCase.registerCommission(event);
    }

    @RabbitListener(queues = MQTopology.PAYLOAD_RECEIVED_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void onPayloadTicket(ChangePayloadStorageEvent event) {
        log.info("Warehouse: received payload ticket — payloadId={}, material={}, tons={}, sellerId={}",
                event.payloadId(), event.materialType(), event.tons(), event.sellerId());

        receivePayloadDelivery.receive(event);
    }
}
