package space.ballistix.demo.shared.module.task.domain.task;

public class TaskCompletionEvent {
    private String id;

    protected TaskCompletionEvent(){}

    public TaskCompletionEvent(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
