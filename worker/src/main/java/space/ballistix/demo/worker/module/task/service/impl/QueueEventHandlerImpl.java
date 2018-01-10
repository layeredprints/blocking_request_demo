package space.ballistix.demo.worker.module.task.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import space.ballistix.demo.shared.module.task.domain.task.TaskCompletionEvent;
import space.ballistix.demo.shared.module.task.domain.task.TaskCreationEvent;
import space.ballistix.demo.worker.module.task.service.QueueEventHandler;
import space.ballistix.demo.worker.module.task.service.TopicSender;

import java.io.IOException;

/**
 * Created by jens on 09.11.17.
 */
@Service
public class QueueEventHandlerImpl implements QueueEventHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final TopicSender topicSender;

    @Autowired
    public QueueEventHandlerImpl(TopicSender topicSender){
        this.topicSender = topicSender;
    }

    @Override
    @JmsListener(destination = "${queue.prefix}"+ "." + "${queue.creation}")
    public void handleCreationEvent(String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Deserialize
            TaskCreationEvent taskCreationEvent = mapper.readValue(message, TaskCreationEvent.class);

            log.info("Received a creation event for task entity with id " + taskCreationEvent.getId());

            // Execute task here
            // ...
            Thread.sleep(10000);

            // Publish finished event to topic
            topicSender.send(new TaskCompletionEvent(taskCreationEvent.getId()));

        } catch (IOException e) {
            log.error("Failed to parse task received from the queue", e.getMessage(), e);
            throw e;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}