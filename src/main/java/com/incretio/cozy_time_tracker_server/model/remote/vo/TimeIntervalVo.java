package com.incretio.cozy_time_tracker_server.model.remote.vo;

import com.incretio.cozy_time_tracker_server.model.local.timeinterval.TimeIntervalManager;
import org.joda.time.DateTime;

public class TimeIntervalVo extends BaseVo {
    private final TimeIntervalManager timeInterval;

    public TimeIntervalVo(TimeIntervalManager timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getStart() {
        return new DateTime(timeInterval.getStart().getTime()).toString("HH:mm:ss");
    }

    public String getStop() {
        return new DateTime(timeInterval.getCalculatedStopInMs()).toString("HH:mm:ss");
    }

    public String getTotal() {
        return new DateTime(timeInterval.getTotalInMs()).toString("HH:mm:ss");
    }

    public String getMessage() {
        return timeInterval.getMessage();
    }
}
