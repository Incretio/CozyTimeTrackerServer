package com.incretio.cozy_time_tracker_server.web;

import com.incretio.cozy_time_tracker_server.service.TimeIntervalService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping ("timeinterval")
public class TimeIntervalController {
    private static final Logger log = getLogger(TaskController.class);

    private final TimeIntervalService timeIntervalService;

    @Autowired
    public TimeIntervalController(TimeIntervalService timeIntervalService) {
        this.timeIntervalService = timeIntervalService;
    }

    @GetMapping(
        value = "workedtoday",
        produces = MediaType.APPLICATION_JSON)
    public String workedToday() {
        return timeIntervalService.workedToday();
    }
}
