package be.kdg.prog6.warehouse.adapter.in.messaging;

import be.kdg.prog6.common.event.ChangePOStatusEvent;
import be.kdg.prog6.warehouse.domain.PONumber;
import be.kdg.prog6.warehouse.port.in.MatchSOAndPOUseCase;
import be.kdg.prog6.warehouse.port.in.WarehouseIssueMaterialsUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WatersideListener {

    private final WarehouseIssueMaterialsUseCase issueMaterialsAmount;
    private final MatchSOAndPOUseCase matchSOAndPOUseCase;
    public static final String FUlFILL_ORDER_STATUS_QUEUE = RabbitMQTopology.FULFILL_ORDER_STATUS_QUEUE;
    public static final String MATCH_ORDER_STATUS_QUEUE = RabbitMQTopology.MATCH_ORDER_STATUS_QUEUE;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public WatersideListener(WarehouseIssueMaterialsUseCase issueMaterialsAmount, MatchSOAndPOUseCase matchPOAndSOUseCase) {
        this.issueMaterialsAmount = issueMaterialsAmount;
        this.matchSOAndPOUseCase = matchPOAndSOUseCase;
    }

    @RabbitListener(queues = FUlFILL_ORDER_STATUS_QUEUE, messageConverter = "jackson2JsonMessageConverter")
    public void fulfillPurchaseOrder(ChangePOStatusEvent event) {
        logger.info("Initiate deducting of payload for PO: %s".formatted(event.poNumber()));
        issueMaterialsAmount.issueMaterialsAmount(new PONumber(event.poNumber()));

    }

    @RabbitListener(queues = MATCH_ORDER_STATUS_QUEUE, messageConverter = "jackson2JsonMessageConverter")
    public void matchPOAndSO(ChangePOStatusEvent event) {
        logger.info("Initiate matching of payload for PO: %s".formatted(event.poNumber()));
        matchSOAndPOUseCase.matchSOandPO(new PONumber(event.poNumber()));
    }
}
