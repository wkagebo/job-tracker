package com.wkagebo.jobtracker.messaging;

import com.wkagebo.jobtracker.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StatusUpdateConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consumeStatusUpdate(StatusUpdateEvent statusUpdateEvent) {
        log.info("Status updated - applicationId: {}, new status: {}",
                statusUpdateEvent.applicationId(),
                statusUpdateEvent.status());
    }

}
