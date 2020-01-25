package com.incretio.cozy_time_tracker_server.model.remote.vo;

import com.incretio.cozy_time_tracker_server.model.local.timeinterval.TimeIntervalManager;

import java.util.Date;

public class TimeIntervalVo extends BaseVo {
    private final TimeIntervalManager timeInterval;

    public TimeIntervalVo(TimeIntervalManager timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Date getStart() {
        return timeInterval.getStart();
    }

    public Date getStop() {
        return timeInterval.getStop();
    }

    public String getMessage() {
        return timeInterval.getMessage();
    }
}
