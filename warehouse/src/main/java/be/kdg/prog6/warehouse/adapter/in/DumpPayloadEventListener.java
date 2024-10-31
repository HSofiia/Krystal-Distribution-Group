package be.kdg.prog6.warehouse.adapter.in;

import be.kdg.prog6.common.event.ConveyorPayloadEvent;
import be.kdg.prog6.warehouse.port.in.PayloadManagementUseCase;
import be.kdg.prog6.warehouse.port.in.ReceiveMaterialAmountCommand;
import be.kdg.prog6.warehouse.port.in.ReceiveMaterialAmountUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DumpPayloadEventListener {
    public static final String PAYLOAD_RECEIVED_QUEUE = "payload_received";

    private static final Logger log = LoggerFactory.getLogger(DumpPayloadEventListener.class);
    private final PayloadManagementUseCase payloadManagementUseCase;
    private final ReceiveMaterialAmountUseCase receiveMaterialAmountUseCase;

    public DumpPayloadEventListener(PayloadManagementUseCase payloadManagementUseCase, ReceiveMaterialAmountUseCase receiveMaterialAmountUseCase) {
        this.payloadManagementUseCase = payloadManagementUseCase;
        this.receiveMaterialAmountUseCase = receiveMaterialAmountUseCase;
    }

    @RabbitListener(queues = PAYLOAD_RECEIVED_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void payloadReceived(final ConveyorPayloadEvent event) {
        log.info(
                "payload info received {}, {}, {}, {}",
                event.licencePlate(),
                event.time(),
                event.materialType(),
                event.warehouseNumber()
        );

        payloadManagementUseCase.savePayload(
                        event.warehouseNumber(),
                        event.time(),
                        event.netWeight()
        );

        receiveMaterialAmountUseCase.receiveMaterial(new ReceiveMaterialAmountCommand(
                event.warehouseNumber(),
                event.materialType(),
                event.netWeight()
        ));
    }
}
