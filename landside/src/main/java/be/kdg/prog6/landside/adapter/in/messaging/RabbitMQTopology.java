package be.kdg.prog6.landside.adapter.in.messaging;

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

    public static final String WAREHOUSE_EVENTS_EXCHANGE = "warehouse_events";
    public static final String PAYLOAD_RECEIVED_QUEUE = "payload_received";

    public static final String PAYLOAD_TICKET_EXCHANGE = "payload_delivery_ticket_exchange";
    public static final String PAYLOAD_TICKET_QUEUE = "payload_delivery_ticket_queue";

//    @Bean
//    TopicExchange warehouseEventsExchange() {
//        return new TopicExchange(WAREHOUSE_EVENTS_EXCHANGE);
//    }
//
//    @Bean
//    Queue weightReceivedQueue() {
//        return new Queue(PAYLOAD_RECEIVED_QUEUE, true);
//    }

    @Bean
    TopicExchange pdtExchange() {
        return new TopicExchange(PAYLOAD_TICKET_EXCHANGE);
    }

    @Bean
    Queue pdtReceivedQueue() {
        return new Queue(PAYLOAD_TICKET_QUEUE);
    }

    @Bean
    Binding binding(TopicExchange exchange, Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("landside.#.pdt.received");
//                .with("warehouse.#.payload.received");
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
