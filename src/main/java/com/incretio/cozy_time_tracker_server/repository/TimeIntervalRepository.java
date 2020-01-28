package com.incretio.cozy_time_tracker_server.repository;

import com.incretio.cozy_time_tracker_server.model.local.timeinterval.TimeIntervalManager;

import java.util.List;

public interface TimeIntervalRepository {

    List<TimeIntervalManager> getAll();

}
