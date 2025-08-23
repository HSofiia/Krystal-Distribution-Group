package be.kdg.prog6.adapter.in.messaging;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQTopology {

    public static final String CHANGE_ORDER_STATUS_EXCHANGE = "change_order_status_exchange";
    public static final String MATCH_ORDER_STATUS_QUEUE = "match_order_status_queue";
    public static final String FUlFILL_ORDER_STATUS_QUEUE = "fulfill_order_status_queue";

    @Bean
    TopicExchange changeOrderStatusExchange() {
        return new TopicExchange(CHANGE_ORDER_STATUS_EXCHANGE);
    }

    @Bean
    Queue fulfillOrderStatusQueue() {
        return new Queue(FUlFILL_ORDER_STATUS_QUEUE);
    }

    @Bean
    Queue matchOrderStatusQueue() {
        return new Queue(MATCH_ORDER_STATUS_QUEUE);
    }

    @Bean
    Binding bindingFulfillOrderStatus(TopicExchange exchange, Queue fulfillOrderStatusQueue) {
        return BindingBuilder.
                bind(fulfillOrderStatusQueue).
                to(exchange).
                with("status.#.issued");
    }


    @Bean
    Binding bindingMatchOrderStatus(TopicExchange exchange, Queue matchOrderStatusQueue) {
        return BindingBuilder.
                bind(matchOrderStatusQueue).
                to(exchange).
                with("status.#.matched");
    }


    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
