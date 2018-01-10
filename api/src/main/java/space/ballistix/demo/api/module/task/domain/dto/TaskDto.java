package space.ballistix.demo.api.module.task.domain.dto;

import java.util.List;

/**
 * Created by jens on 03.11.17.
 */

public class TaskDto {
    private Long id;
    private String originator;
    private String body;
    private List<String> recipients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }
}
