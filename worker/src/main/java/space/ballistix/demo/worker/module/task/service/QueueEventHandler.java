package space.ballistix.demo.worker.module.task.service;

import java.io.IOException;

/**
 * Created by jens on 09.11.17.
 */
public interface QueueEventHandler {
    void handleCreationEvent(String json) throws IOException;
}
