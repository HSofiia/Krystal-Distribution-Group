package be.kdg.prog6.warehouse.adapter.in;

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


    public static final String PAYLOAD_RECEIVED_QUEUE = "payload_received";

    @Bean
    TopicExchange warehouseCapacityExchange() {
        return new TopicExchange(LANDSIDE_EXCHANGE);
    }

    @Bean
    Queue warehouseCapacityQueue() {
        return new Queue(WAREHOUSE_CAPACITY_QUEUED, true);
    }

    @Bean
    Queue weightReceivedQueue() {
        return new Queue(PAYLOAD_RECEIVED_QUEUE, true);
    }

    @Bean
    Binding bindingPayloadReceived(TopicExchange exchange, Queue weightReceivedQueue) {
        return BindingBuilder
                .bind(weightReceivedQueue)
                .to(exchange)
                .with("warehouse.#.payload.received");
    }

    @Bean
    Binding bindingCapacityChanged(TopicExchange warehouseCapacityExchange, Queue warehouseCapacityQueue) {
        return BindingBuilder
                .bind(warehouseCapacityQueue)
                .to(warehouseCapacityExchange)
                .with("warehouse.#.capacity.changed");
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
