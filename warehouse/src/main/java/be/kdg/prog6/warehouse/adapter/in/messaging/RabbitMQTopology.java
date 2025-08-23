package be.kdg.prog6.warehouse.adapter.in.messaging;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopology {

    public static final String LANDSIDE_EXCHANGE = "landside_events";
    public static final String WAREHOUSE_CAPACITY_QUEUED = "warehouse_capacity_queue";

    public static final String COMMISSIONS_QUEUE = "commissions_queue";

    public static final String PAYLOAD_TICKET_EXCHANGE = "payload_delivery_ticket_exchange";
    public static final String PAYLOAD_DELIVERY_TICKET_QUEUE = "payload_delivery_ticket_queue";

    @Bean
    TopicExchange pdtExchange() {
        return new TopicExchange(PAYLOAD_TICKET_EXCHANGE, true, false);
    }

    @Bean
    Queue payloadDeliveryTicketQueue() {
        return new Queue(PAYLOAD_DELIVERY_TICKET_QUEUE, true);
    }

    @Bean
    Binding bindingPdt(TopicExchange pdtExchange, Queue payloadDeliveryTicketQueue) {
        return BindingBuilder.bind(payloadDeliveryTicketQueue)
                .to(pdtExchange)
                .with("landside.#.pdt.received");
    }

    @Bean
    TopicExchange warehouseCapacityExchange() {
        return new TopicExchange(LANDSIDE_EXCHANGE);
    }

    @Bean
    Queue warehouseCapacityQueue() {
        return new Queue(WAREHOUSE_CAPACITY_QUEUED, true);
    }

    @Bean
    Binding bindingCapacityChanged(TopicExchange warehouseCapacityExchange, Queue warehouseCapacityQueue) {
        return BindingBuilder
                .bind(warehouseCapacityQueue)
                .to(warehouseCapacityExchange)
                .with("warehouse.#.capacity.updated");
    }

    public static final String INVOICING_EXCHANGE = "invoicing_exchange";
//    public static final String PAYLOAD_RECEIVED_QUEUE = "payload_received_queue";
//
//    @Bean
//    TopicExchange invoiceExchange() {
//        return new TopicExchange(INVOICING_EXCHANGE);
//    }
//
//    @Bean
//    Queue payloadReceivedQueue() {
//        return new Queue(PAYLOAD_RECEIVED_QUEUE);
//    }
//
//    @Bean
//    Binding bindingPayloadReceived(TopicExchange invoiceExchange, Queue payloadReceivedQueue) {
//        return BindingBuilder
//                .bind(payloadReceivedQueue)
//                .to(invoiceExchange)
//                .with("payload.#.delivered");
//    }

    @Bean
    TopicExchange invoicingExchange() {
        return new TopicExchange(INVOICING_EXCHANGE, true, false);
    }

    @Bean
    Queue commissionsQueue() {
        return new Queue(COMMISSIONS_QUEUE, true);
    }

    @Bean
    Binding commissionsBinding(TopicExchange invoicingExchange, Queue commissionsQueue) {
        return BindingBuilder.bind(commissionsQueue)
                .to(invoicingExchange)
                .with("po.#.commission.request");
    }

    public static final String CHANGE_ORDER_STATUS_EXCHANGE = "change_order_status_exchange";
    public static final String FULFILL_ORDER_STATUS_QUEUE = "fulfill_order_status_queue";
    public static final String MATCH_ORDER_STATUS_QUEUE = "match_order_status_queue";

    @Bean TopicExchange changeOrderStatusExchange() {
        return new TopicExchange(CHANGE_ORDER_STATUS_EXCHANGE, true, false);
    }

    @Bean Queue fulfillOrderStatusQueue() {
        return new Queue(FULFILL_ORDER_STATUS_QUEUE, true);
    }

    @Bean Queue matchOrderStatusQueue() {
        return new Queue(MATCH_ORDER_STATUS_QUEUE, true);
    }

    @Bean Binding bindingFulfillOrderStatus(TopicExchange changeOrderStatusExchange,
                                            Queue fulfillOrderStatusQueue) {
        return BindingBuilder.bind(fulfillOrderStatusQueue)
                .to(changeOrderStatusExchange)
                .with("status.#.issued");
    }

    @Bean Binding bindingMatchOrderStatus(TopicExchange changeOrderStatusExchange,
                                          Queue matchOrderStatusQueue) {
        return BindingBuilder.bind(matchOrderStatusQueue)
                .to(changeOrderStatusExchange)
                .with("status.#.matched");
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
