package space.ballistix.demo.api.module.task.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.ballistix.demo.api.module.task.domain.dto.TaskCreationDto;
import space.ballistix.demo.api.module.task.service.TaskService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


/**
 * Created by jens on 03.11.17.
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Future<ResponseEntity> create(@RequestBody TaskCreationDto taskCreationDto) {
        CompletableFuture<ResponseEntity> future = new CompletableFuture<>();
        taskService.create(taskCreationDto, future);
        return future;
    }
}