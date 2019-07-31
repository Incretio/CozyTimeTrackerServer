package com.incretio.cozy_time_tracker_server.dao;

import com.incretio.cozy_time_tracker_server.model.ex.Task;
import com.incretio.cozy_time_tracker_server.model.pojo.TaskStatus;
import com.incretio.cozy_time_tracker_server.model.vi.TaskVi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TasksDAO {
    private List<Task> tasksList = new ArrayList<>();

    // @formatter:off
    {
        Task task = new Task(
            1,
            new ArrayList<>(Arrays.asList(1,2)),
            "LNDR-0001",
            "Остаток на складах шоурум выводить отдельно (ТЗ)",
            "",
            0,
            0,
            10*60*60*1000,
            TaskStatus.STOPPED
        );
        tasksList.add(task);
        task = new Task(
            2,
            1,
            "LNDR-0002",
            "Остаток на складах шоурум выводить отдельно (ТЗ)",
            "",
            0,
            0,
            60*1000,
            TaskStatus.STOPPED
        );
        tasksList.add(task);
        task = new Task(
            3,
            1,
            "LNDR-0003",
            "Остаток на складах шоурум выводить отдельно (ТЗ)",
            "",
            5*60*60+38,
            5*60*60+38,
            10*60*60,
            TaskStatus.STOPPED
        );
        tasksList.add(task);
        task = new Task(
            4,
            2,
            "LNDR-0004",
            "Остаток на складах шоурум выводить отдельно (ТЗ)",
            "",
            5*60*60+38,
            5*60*60+38,
            10*60*60,
            TaskStatus.STOPPED
        );
        tasksList.add(task);
        task = new Task(
            5,
            2,
            "LNDR-0005",
            "Остаток на складах шоурум выводить отдельно (ТЗ)",
            "",
            5*60*60+38,
            5*60*60+38,
            10*60*60,
            TaskStatus.STOPPED
        );
        tasksList.add(task);
        task = new Task(
            6,
            2,
            "LNDR-0006",
            "Остаток на складах шоурум выводить отдельно (ТЗ)",
            "",
            5*60*60+38,
            5*60*60+38,
            10*60*60,
            TaskStatus.STOPPED
        );
        tasksList.add(task);
        task = new Task(
            7,
            3,
            "LNDR-0007",
            "Остаток на складах шоурум выводить отдельно (ТЗ)",
            "",
            5*60*60+38,
            5*60*60+38,
            10*60*60,
            TaskStatus.STOPPED
        );
        tasksList.add(task);
        task = new Task(
            8,
            3,
            "LNDR-0008",
            "Остаток на складах шоурум выводить отдельно (ТЗ)",
            "",
            5*60*60+38,
            5*60*60+38,
            10*60*60,
            TaskStatus.STOPPED
        );
        tasksList.add(task);
        task = new Task(
            9,
            3,
            "LNDR-0009",
            "Остаток на складах шоурум выводить отдельно (ТЗ)",
            "",
            5*60*60+38,
            5*60*60+38,
            10*60*60,
            TaskStatus.STOPPED
        );
        tasksList.add(task);
    }
    // @formatter:on

    public List<Task> getTasksList() {
        return tasksList;
    }

    public List<Task> getTasksList(int tagId) {
        if (tagId == 0) {
            return getTasksList();
        }
        return tasksList.stream().filter(task -> task.getTagsList().contains(tagId))
                        .collect(Collectors.toList());
    }

    public Optional<Task> getTask(int taskId) {
        return tasksList.stream()
                        .filter(task -> task.getId() == taskId)
                        .findFirst();
    }

    public List<Task> getTasksWithExclude(int taskId) {
        return tasksList.stream()
                        .filter(task -> task.getId() != taskId)
                        .collect(Collectors.toList());
    }

    public Task addTask(String number, String name, int tagId) {
        Task addedTask = new Task(tasksList.size() + 1, tagId, number, name, "", 0, 0, 0, TaskStatus.STOPPED);
        tasksList.add(addedTask);
        return addedTask;
    }

    public Task update(int taskId, TaskVi taskVi) {
        Task task = getTask(taskId).orElseThrow();
        task.fillFrom(taskVi);
        return task;
    }
}
