package space.ballistix.demo.worker.module.task.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import space.ballistix.demo.shared.container.configuration.QueueConfig;
import space.ballistix.demo.shared.module.task.domain.task.TaskCompletionEvent;
import space.ballistix.demo.worker.module.task.service.TopicSender;

@Service
public class TopicSenderImpl implements TopicSender{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final JmsTemplate jmsTemplate;
    private final QueueConfig queueConfig;

    @Autowired
    public TopicSenderImpl(JmsTemplate jmsTemplate, QueueConfig queueConfig){
        this.jmsTemplate = jmsTemplate;
        this.queueConfig = queueConfig;
    }

    @Override
    public void send(TaskCompletionEvent event) {
        this.jmsTemplate.convertAndSend(new ActiveMQTopic(queueConfig.getPrefix() + "." + queueConfig.getCompletion()), toJson(event));
        log.info("A completion event has been published for task with id: " + event.getId());
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
