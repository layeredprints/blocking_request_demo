package space.ballistix.demo.api.module.task.service;

import org.springframework.http.ResponseEntity;
import space.ballistix.demo.api.module.task.domain.dto.TaskCreationDto;

import java.util.concurrent.CompletableFuture;

/**
 * Created by jens on 10.01.18.
 */
public interface TaskService {
    void create(TaskCreationDto taskCreationDto, CompletableFuture<ResponseEntity> future);
}
