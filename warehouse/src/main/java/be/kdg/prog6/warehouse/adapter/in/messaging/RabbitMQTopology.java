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

    public static final String INVOICING_EXCHANGE = "invoicing_exchange";
    public static final String PAYLOAD_RECEIVED_QUEUE = "payload_received_queue";

    public static final String COMMISSIONS_QUEUE = "commissions_queue";

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

    @Bean
    TopicExchange invoiceExchange() {
        return new TopicExchange(INVOICING_EXCHANGE);
    }

    @Bean
    Queue payloadReceivedQueue() {
        return new Queue(PAYLOAD_RECEIVED_QUEUE);
    }

    @Bean
    Binding bindingPayloadReceived(TopicExchange invoiceExchange, Queue payloadReceivedQueue) {
        return BindingBuilder
                .bind(payloadReceivedQueue)
                .to(invoiceExchange)
                .with("payload.#.delivered");
    }

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
                .with("po.%s.commission.request");
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
