package com.incretio.cozy_time_tracker_server.model.local.timeinterval;

import com.incretio.cozy_time_tracker_server.model.remote.vo.TimeIntervalVo;
import com.incretio.cozy_time_tracker_server.model.remote.vo.helper.ToVoConvertable;
import org.joda.time.DateTime;

import java.util.Date;

public class TimeIntervalManager implements ToVoConvertable<TimeIntervalVo>  {
    private final TimeIntervalPOJO pojo;

    public TimeIntervalManager(TimeIntervalPOJO pojo) {
        this.pojo = pojo;
    }

    public TimeIntervalPOJO getPojo() {
        return pojo;
    }

    @Override
    public TimeIntervalVo toVo() {
        return new TimeIntervalVo(this);
    }

    public void setStop(Date date) {
        pojo.setStop(date);
    }

    public Date getStart() {
        return pojo.getStart();
    }

    public Date getStop() {
        return pojo.getStop();
    }

    public String getMessage() {
        return pojo.getMessage();
    }

    public long getTodayIntervalDurationInMs() {
        return getIntervalDurationImMs(new Date());
    }

    public long getIntervalDurationImMs(Date date) {
        if (getStart() == null) {
            return 0;
        }
        long startDayInMs = new DateTime(date).withTimeAtStartOfDay().getMillis();
        long stopDayInMs = new DateTime(date).plusDays(1).withTimeAtStartOfDay().getMillis();
        long startInMs = getStart().getTime();
        long stopInMs = getCalculatedStopInMs();

        if (stopInMs < startDayInMs || startInMs > stopDayInMs) {
            return 0;
        }
        long beforeDateInMs = startDayInMs - startInMs;
        beforeDateInMs = (beforeDateInMs < 0 ? 0 : beforeDateInMs);
        long afterDateInMs = stopInMs - stopDayInMs;
        afterDateInMs = (afterDateInMs < 0 ? 0 : afterDateInMs);
        return (stopInMs - startInMs) - beforeDateInMs - afterDateInMs;
    }

    public long getIntervalDurationInMs() {
        if (getStart() == null) {
            return 0;
        }
        return getCalculatedStopInMs() - getStart().getTime();
    }

    public long getCalculatedStopInMs() {
        if (getStop() == null) {
            return new Date().getTime();
        } else {
            return getStop().getTime();
        }
    }

    public long getTotalInMs() {
        long diff = getCalculatedStopInMs() - getStart().getTime();
        return DateTime.now().withTimeAtStartOfDay().plusMillis((int)diff).getMillis();
    }

}
