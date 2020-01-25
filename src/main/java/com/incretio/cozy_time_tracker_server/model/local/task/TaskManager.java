package com.incretio.cozy_time_tracker_server.model.local.task;

import com.incretio.cozy_time_tracker_server.model.local.tag.TagManager;
import com.incretio.cozy_time_tracker_server.model.local.timeinterval.TimeIntervalManager;
import com.incretio.cozy_time_tracker_server.model.local.taskstatus.TaskStatus;
import com.incretio.cozy_time_tracker_server.model.local.timeinterval.TimeIntervalPOJO;
import com.incretio.cozy_time_tracker_server.model.remote.vi.TaskVi;
import com.incretio.cozy_time_tracker_server.model.remote.vo.TaskVo;
import com.incretio.cozy_time_tracker_server.model.remote.vo.helper.ToVoConvertable;
import com.incretio.cozy_time_tracker_server.utils.Condition;
import com.incretio.cozy_time_tracker_server.utils.DateTimeUtil;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager implements ToVoConvertable<TaskVo> {
    private final TaskPOJO pojo;

    public TaskManager(TaskPOJO pojo) {
        this.pojo = pojo;
    }

    public TaskPOJO getPojo() {
        return pojo;
    }

    private long startedTimeInMs = 0;

    @Override
    public TaskVo toVo() {
        return new TaskVo(this);
    }

    public TaskManager toggle() {
        Condition.build(pojo.getTaskStatus())
                 .ifEqualsThen(TaskStatus.STARTED, this::stop)
                 .ifEqualsThen(TaskStatus.STOPPED, this::start);
        return this;
    }

    private void start() {
        pojo.setTaskStatus(TaskStatus.STARTED);
        startedTimeInMs = DateTimeUtil.nowInMs();
        TimeIntervalPOJO timeInterval = new TimeIntervalPOJO();
        timeInterval.setStart(new Date(startedTimeInMs));
        pojo.getTimeIntervalPOJOList().add(timeInterval);
        // save
    }

    public void stop() {
        if (pojo.getTaskStatus() != TaskStatus.STARTED) {
            return;
        }
        pojo.setTaskStatus(TaskStatus.STOPPED);
        long stoppedTimeInMs = DateTimeUtil.nowInMs();
        long leftTimeInMs = stoppedTimeInMs - startedTimeInMs;
        incTimeLeft(leftTimeInMs);
        startedTimeInMs = 0;
        List<TimeIntervalPOJO> timeIntervalList = pojo.getTimeIntervalPOJOList();
        if (!timeIntervalList.isEmpty() && timeIntervalList.get(getTimeIntervalList().size() - 1).getStop() == null) {
            getTimeIntervalList().get(getTimeIntervalList().size() - 1).setStop(new Date(stoppedTimeInMs));
        }
        // save
    }

    private void incTimeLeft(long ms) {
        pojo.setTimeLeftToday(pojo.getTimeLeftToday() + ms);
        pojo.setTimeLeftAll(pojo.getTimeLeftAll() + ms);
    }

    public void fillFrom(TaskVi taskVi) {
        pojo.setNumber(taskVi.getNumber());
        pojo.setName(taskVi.getName());
        pojo.setTagsList(taskVi.getTagsList());
    }

    public List<TimeIntervalManager> getTimeIntervalList(){
        return pojo.getTimeIntervalPOJOList()
            .stream()
            .map((TimeIntervalManager::new))
            .collect(Collectors.toList());
    }

    public int getProgress() {
        if (pojo.getTimeLimit() == 0) {
            return 0;
        } else if (getTimeLeftAllInMs() >= pojo.getTimeLimit()) {
            return 100;
        } else {
            return (int) Math.round(1.0 * getTimeLeftAllInMs() / pojo.getTimeLimit() * 100);
        }
    }

    public List<Integer> getTagsList() {
        return pojo.getTagsList();
    }

    public int getId() {
        return pojo.getId();
    }

    public TaskStatus getTaskStatus() {
        return pojo.getTaskStatus();
    }

    public long getTimeLeftTodayInMs() {
        return pojo.getTimeIntervalPOJOList().stream()
                   .map(TimeIntervalManager::new)
                   .mapToLong(TimeIntervalManager::getTodayIntervalDurationInMs)
                   .sum();
    }

    public long getTimeLeftAllInMs() {
        return pojo.getTimeIntervalPOJOList().stream()
            .map(TimeIntervalManager::new)
            .mapToLong(TimeIntervalManager::getIntervalDurationInMs)
            .sum();
    }

    public long getTimeLimit() {
        return pojo.getTimeLimit();
    }

    public String getNumber() {
        return pojo.getNumber();
    }

    public String getName() {
        return pojo.getName();
    }

    public String getDescription() {
        return pojo.getDescription();
    }
}


