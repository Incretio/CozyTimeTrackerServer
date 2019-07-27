package com.incretio.cozy_time_tracker_server.model.ex;

import com.incretio.cozy_time_tracker_server.model.pojo.TaskPOJO;
import com.incretio.cozy_time_tracker_server.model.pojo.TaskStatus;
import com.incretio.cozy_time_tracker_server.model.vo.TaskVo;
import com.incretio.cozy_time_tracker_server.model.vo.helper.ToVoConvertable;
import com.incretio.cozy_time_tracker_server.utils.DateTimeUtil;

import java.util.Objects;

public class Task extends TaskPOJO implements ToVoConvertable<TaskVo> {
    public Task(int id, int tagId, String number, String name, String description, long timeLeftToday, long timeLeftAll,
                long timeLimit, TaskStatus taskStatus) {
        super(id, tagId, number, name, description, timeLeftToday, timeLeftAll, timeLimit, taskStatus);
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
}

class Condition {
    private Object value;
    private boolean executed = false;

    private Condition() {
        // noop
    }

    private Condition(Object value) {
        this.value = value;
    }

    static Condition build() {
        return new Condition();
    }

    static Condition build(Object value) {
        return new Condition(value);
    }

    Condition ifThen(boolean value, Runnable action) {
        if (value) {
            action.run();
        }
        return this;
    }

    Condition elseIfThen(boolean value, Runnable action) {
        if (!executed && value) {
            action.run();
        }
        return this;
    }

    void elseThen(Runnable action) {
        if (!executed) {
            action.run();
        }
    }

    Condition ifEqualsThen(Object value, Runnable action) {
        if (this.value == null) {
            throw new UnsupportedOperationException("Value of Condition is not define!");
        }
        ifThen(Objects.equals(this.value, value), action);
        return this;
    }

    Condition elseIfEqualsThen(Object value, Runnable action) {
        if (this.value == null) {
            throw new UnsupportedOperationException("Value of Condition is not define!");
        }
        if (!executed) {
            ifEqualsThen(Objects.equals(this.value, value), action);
        }
        return this;
    }
}
