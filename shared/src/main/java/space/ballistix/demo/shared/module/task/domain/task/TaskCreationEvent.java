package space.ballistix.demo.shared.module.task.domain.task;

/**
 * Created by jens on 09.11.17.
 */
public class TaskCreationEvent {
    private String id;

    protected TaskCreationEvent() {}

    public TaskCreationEvent(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
