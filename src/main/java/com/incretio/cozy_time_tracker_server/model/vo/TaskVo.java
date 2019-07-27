package com.incretio.cozy_time_tracker_server.model.vo;

import com.incretio.cozy_time_tracker_server.model.ex.Task;
import com.incretio.cozy_time_tracker_server.utils.DateTimeUtil;

public class TaskVo extends BaseVo {
    private final Task task;

    public TaskVo(Task task) {
        this.task = task;
    }

    public int getId() {
        return task.getId();
    }

    public int getTagId() {
        return task.getTagId();
    }

    public String getTaskStatus() {
        return task.getTaskStatus()
                   .toString();
    }

    public int getProgress() {
        if (task.getTimeLimit() == 0) {
            return 0;
        } else if (task.getTimeLeftAll() >= task.getTimeLimit()) {
            return 100;
        } else {
            return (int) Math.round(1.0 * task.getTimeLeftAll() / task.getTimeLimit() * 100);
        }
    }

    public String getTimeLeftToday() {
        return DateTimeUtil.msToTimeString(task.getTimeLeftToday());
    }

    public String getTimeLeftAll() {
        return DateTimeUtil.msToTimeString(task.getTimeLeftAll());
    }

    public String getTimeLimit() {
        return DateTimeUtil.msToTimeString(task.getTimeLimit());
    }

    public String getNumber() {
        return task.getNumber();
    }

    public String getName() {
        return task.getName();
    }

    public String getDescription() {
        return task.getDescription();
    }

}
