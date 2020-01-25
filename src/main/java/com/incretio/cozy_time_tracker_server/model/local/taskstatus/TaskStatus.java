package com.incretio.cozy_time_tracker_server.model.local.taskstatus;

public enum TaskStatus {
    STARTED,
    STOPPED;

    @Override
    public String toString() {
        return name();
    }
}
