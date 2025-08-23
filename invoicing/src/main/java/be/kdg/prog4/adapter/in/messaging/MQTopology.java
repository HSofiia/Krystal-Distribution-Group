package be.kdg.prog4.adapter.in.messaging;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQTopology {

    public static final String INVOICING_EXCHANGE     = "invoicing_exchange";
    public static final String COMMISSIONS_QUEUE      = "commissions_queue";
    public static final String PAYLOAD_RECEIVED_QUEUE = "payload_received_queue";

    @Bean
    TopicExchange invoicingExchange() {
        return new TopicExchange(INVOICING_EXCHANGE);
    }

    @Bean
    Queue commissionsQueue() {
        return new Queue(COMMISSIONS_QUEUE, true);
    }

    @Bean
    Queue payloadDeliveryQueue() {
        return new Queue(PAYLOAD_RECEIVED_QUEUE, true);
    }

    @Bean
    Binding bindCommission(TopicExchange invoicingExchange, Queue commissionsQueue) {
        return BindingBuilder.bind(commissionsQueue).to(invoicingExchange).with("po.%s.commission.request");
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}

