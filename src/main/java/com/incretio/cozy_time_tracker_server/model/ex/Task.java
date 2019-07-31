package com.incretio.cozy_time_tracker_server.model.ex;

import com.incretio.cozy_time_tracker_server.model.pojo.TaskPOJO;
import com.incretio.cozy_time_tracker_server.model.pojo.TaskStatus;
import com.incretio.cozy_time_tracker_server.model.vi.TaskVi;
import com.incretio.cozy_time_tracker_server.model.vo.TaskVo;
import com.incretio.cozy_time_tracker_server.model.vo.helper.ToVoConvertable;
import com.incretio.cozy_time_tracker_server.utils.Condition;
import com.incretio.cozy_time_tracker_server.utils.DateTimeUtil;

import java.util.Collections;
import java.util.List;

public class Task extends TaskPOJO implements ToVoConvertable<TaskVo> {
    public Task(int id, List<Integer> tagsList, String number, String name, String description, long timeLeftToday,
                long timeLeftAll,
                long timeLimit, TaskStatus taskStatus) {
        super(id, tagsList, number, name, description, timeLeftToday, timeLeftAll, timeLimit, taskStatus);
    }

    public Task(int id, int tagsList, String number, String name, String description, long timeLeftToday,
                long timeLeftAll, long timeLimit, TaskStatus taskStatus) {
        this(id, Collections.singletonList(tagsList), number, name, description, timeLeftToday, timeLeftAll, timeLimit,
             taskStatus);
    }

    public Task() {
        setTaskStatus(TaskStatus.STOPPED);
    }

    private long startedTimeInMs = 0;

    @Override
    public TaskVo toVo() {
        return new TaskVo(this);
    }

    public Task toggle() {
        Condition.build(getTaskStatus())
                 .ifEqualsThen(TaskStatus.STARTED, this::stop)
                 .ifEqualsThen(TaskStatus.STOPPED, this::start);
        return this;
    }

    private void start() {
        setTaskStatus(TaskStatus.STARTED);
        startedTimeInMs = DateTimeUtil.nowInMs();
    }

    public void stop() {
        if (getTaskStatus() != TaskStatus.STARTED) {
            return;
        }
        setTaskStatus(TaskStatus.STOPPED);
        long leftTimeInMs = DateTimeUtil.nowInMs() - startedTimeInMs;
        incTimeLeft(leftTimeInMs);
        startedTimeInMs = 0;
    }

    private void incTimeLeft(long ms) {
        setTimeLeftToday(getTimeLeftToday() + ms);
        setTimeLeftAll(getTimeLeftAll() + ms);
    }

    public void fillFrom(TaskVi taskVi) {
        setNumber(taskVi.getNumber());
        setName(taskVi.getName());
    }
}


