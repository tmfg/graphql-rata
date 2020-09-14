package graphqlscope.graphql.entities;

import java.time.ZonedDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class LiveTimeTableTrain {
    @EmbeddedId
    public TrainId id;
    public Long version;
    public String stationShortCode;

    public boolean trainStopping;
    public ZonedDateTime scheduledTime;
    public ZonedDateTime liveEstimateTime;
    public ZonedDateTime actualTime;
    public Long trainCategoryId;
    public TimeTableRow.TimeTableRowType type;
}
