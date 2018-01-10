package space.ballistix.demo.worker.module.task.service;

import space.ballistix.demo.shared.module.task.domain.task.TaskCompletionEvent;

public interface TopicSender {
    void send(TaskCompletionEvent taskCompletionEvent);
}
