package fi.digitraffic.graphql.rail.entities;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CompositionTimeTableRow {
    @Id
    public Long id;

    public String stationShortCode;
    public int stationUICCode;
    public String countryCode;
    public TimeTableRow.TimeTableRowType type;
    public ZonedDateTime scheduledTime;

    protected CompositionTimeTableRow() {
    }
}
