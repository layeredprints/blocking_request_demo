package space.ballistix.demo.api.module.task.domain.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by jens on 03.11.17.
 */
public class TaskCreationDto {
    private boolean async;

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
