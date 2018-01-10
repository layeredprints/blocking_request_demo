package space.ballistix.demo.api.module.task.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import space.ballistix.demo.api.module.task.domain.dto.TaskCreationDto;
import space.ballistix.demo.api.module.task.domain.dto.TaskCreationResponseDto;
import space.ballistix.demo.api.module.task.service.QueueSender;
import space.ballistix.demo.api.module.task.service.TaskService;
import space.ballistix.demo.shared.module.task.domain.task.TaskCompletionEvent;
import space.ballistix.demo.shared.module.task.domain.task.TaskCreationEvent;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created by jens on 03.11.17.
 */

@Service
public class TaskServiceImpl implements TaskService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final QueueSender queueSender;
    private Map<String, CompletableFuture<ResponseEntity>> futureMap;

    @Autowired
    public TaskServiceImpl(QueueSender queueSender){
        this.queueSender = queueSender;
        this.futureMap = new HashMap<>();
    }


    @Override
    public void create(TaskCreationDto taskCreationDto, CompletableFuture<ResponseEntity> future) {
        if(taskCreationDto.isAsync()){
            // Return immediately
            future.complete(new ResponseEntity(TaskCreationResponseDto.of(Arrays.asList("url-to-resource")), HttpStatus.CREATED));
        }
        else {
            // Return when worker is ready

            // Store Task in database here
            // ...

            // Generate correlation id
            String id = UUID.randomUUID().toString();

            // Store future in futureMap to use when the result comes back
            futureMap.put(id, future);

            // Send event to the queue
            queueSender.send(new TaskCreationEvent(id));
        }
    }

    @JmsListener(destination = "${queue.prefix}"+ "." + "${queue.completion}", containerFactory = "topicListenerFactory")
    public void handleCompletionEvent(String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Deserialize
            TaskCompletionEvent taskCompletionEvent = mapper.readValue(message, TaskCompletionEvent.class);
            log.info("Received a completion event for task entity with id " + taskCompletionEvent.getId());

            // Return blocked API call (if applicable)
            if(futureMap.containsKey(taskCompletionEvent.getId())){
                futureMap.get(taskCompletionEvent.getId()).complete(new ResponseEntity(TaskCreationResponseDto.of(Arrays.asList("url-to-resource")), HttpStatus.CREATED));
                futureMap.remove(taskCompletionEvent.getId());
            }
        } catch (IOException e) {
            log.error("Failed to parse task received from the queue", e.getMessage(), e);
            throw e;
        }
    }
}