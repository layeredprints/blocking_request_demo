package space.ballistix.demo.api.module.task.service;

import space.ballistix.demo.shared.module.task.domain.task.TaskCreationEvent;

/**
 * Created by jens on 09.11.17.
 */
public interface QueueSender {
    void send(TaskCreationEvent event);
}
