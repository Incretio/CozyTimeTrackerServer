package com.incretio.cozy_time_tracker_server.service;

import com.incretio.cozy_time_tracker_server.model.local.timeinterval.TimeIntervalManager;
import com.incretio.cozy_time_tracker_server.repository.TimeIntervalRepository;
import com.incretio.cozy_time_tracker_server.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeIntervalService {
    private final TimeIntervalRepository timeIntervalRepository;

    @Autowired
    public TimeIntervalService(TimeIntervalRepository timeIntervalRepository) {
        this.timeIntervalRepository = timeIntervalRepository;
    }

    public String workedToday() {
        long time = timeIntervalRepository.getAll().stream()
            .mapToLong(TimeIntervalManager::getIntervalDurationInMs)
            .sum();
        return DateTimeUtil.msToTimeString(time);
    }
}
