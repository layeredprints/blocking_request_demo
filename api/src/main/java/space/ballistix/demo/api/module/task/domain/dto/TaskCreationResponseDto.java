package space.ballistix.demo.api.module.task.domain.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jens on 03.11.17.
 */
public class TaskCreationResponseDto {
    private List<String> locations;

    private TaskCreationResponseDto(Collection<String> locations){
        this.locations = new ArrayList<>();
        this.locations.addAll(locations);
    }

    public static TaskCreationResponseDto of(Collection<String> locations){
        checkNotNull(locations, "locations should not be null");
        return new TaskCreationResponseDto(locations);
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
