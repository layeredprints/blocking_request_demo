package space.ballistix.demo.api.module.task.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import space.ballistix.demo.api.module.task.service.QueueSender;
import space.ballistix.demo.shared.container.configuration.QueueConfig;
import space.ballistix.demo.shared.module.task.domain.task.TaskCreationEvent;

/**
 * Created by jens on 08.11.17.
 */
@Service
public class QueueSenderImpl implements QueueSender {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final JmsTemplate jmsTemplate;
    private final QueueConfig queueConfig;

    @Autowired
    public QueueSenderImpl(JmsTemplate jmsTemplate, QueueConfig queueConfig){
        this.jmsTemplate = jmsTemplate;
        this.queueConfig = queueConfig;
    }

    @Override
    public void send(TaskCreationEvent event) {
        this.jmsTemplate.convertAndSend(queueConfig.getPrefix() + "." + queueConfig.getCreation(), toJson(event));
    }

    private String toJson(Object object){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert object to JSON", e.getMessage(), e);
        }
        return null;
    }
}

