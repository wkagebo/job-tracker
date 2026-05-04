package com.wkagebo.jobtracker.messaging;

import com.wkagebo.jobtracker.config.RabbitMQConfig;
import com.wkagebo.jobtracker.model.ApplicationStatus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class StatusUpdateProducer {

    private final AmqpTemplate amqpTemplate;

    public StatusUpdateProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendStatusUpdate(Long applicationId, ApplicationStatus status) {
        StatusUpdateEvent event = new StatusUpdateEvent(applicationId, status);
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, event);
    }

}
