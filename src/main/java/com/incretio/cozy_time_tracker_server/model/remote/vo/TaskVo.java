package com.incretio.cozy_time_tracker_server.model.remote.vo;

import com.incretio.cozy_time_tracker_server.model.local.task.TaskManager;
import com.incretio.cozy_time_tracker_server.model.remote.vo.helper.ConvertVo;
import com.incretio.cozy_time_tracker_server.utils.DateTimeUtil;

import java.util.Collections;
import java.util.List;

public class TaskVo extends BaseVo {
    private final TaskManager task;

    public TaskVo(TaskManager task) {
        this.task = task;
    }

    public int getId() {
        return task.getId();
    }

    public List<Integer> getTagsList() {
        return task.getTagsList();
    }

    public String getStatus() {
        return task.getTaskStatus()
                   .toString();
    }

    public int getProgress() {
        return task.getProgress();
    }

    public String getTimeLeftToday() {
        return DateTimeUtil.msToTimeString(task.getTimeLeftTodayInMs());
    }

    public String getTimeLeftAll() {
        return DateTimeUtil.msToTimeString(task.getTimeLeftAllInMs());
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

    public List<TimeIntervalVo> getTimeIntervalList() {
        List<TimeIntervalVo> result = ConvertVo.toVo(task.getTimeIntervalList());
        Collections.reverse(result);
        return result;
    }

}
