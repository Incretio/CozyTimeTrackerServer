package com.incretio.cozy_time_tracker_server.repository.memory;

import com.incretio.cozy_time_tracker_server.model.local.task.TaskManager;
import com.incretio.cozy_time_tracker_server.model.local.task.TaskPOJO;
import com.incretio.cozy_time_tracker_server.model.local.taskstatus.TaskStatus;
import com.incretio.cozy_time_tracker_server.model.remote.vi.TaskVi;
import com.incretio.cozy_time_tracker_server.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryTaskRepository implements TaskRepository {
    private List<TaskPOJO> tasksList = new ArrayList<>();

    // @formatter:off
    {
        TaskPOJO task = TaskPOJO.builder()
            .id(1)
            .tagsList(new ArrayList<>(Arrays.asList(1,2)))
            .number("LNDR-0001")
            .name("Остаток на складах шоурум выводить отдельно (ТЗ)")
            .timeLimit(10*60*60*1000)
            .build();
        tasksList.add(task);

        task = TaskPOJO.builder()
            .id(2)
            .tagsList(new ArrayList<>(Arrays.asList(1)))
            .number("LNDR-0002")
            .name("Остаток на складах шоурум выводить отдельно (ТЗ)")
            .timeLimit(60*1000)
            .build();
        tasksList.add(task);

        task = TaskPOJO.builder()
            .id(3)
            .tagsList(new ArrayList<>(Arrays.asList(2,3)))
            .number("LNDR-0003")
            .name("Остаток на складах шоурум выводить отдельно (ТЗ)")
            .timeLimit(60*1000)
            .build();
        tasksList.add(task);

        task = TaskPOJO.builder()
            .id(4)
            .tagsList(new ArrayList<>(Arrays.asList(3,4)))
            .number("LNDR-0004")
            .name("Остаток на складах шоурум выводить отдельно (ТЗ)")
            .timeLimit(60*1000)
            .build();
        tasksList.add(task);

        task = TaskPOJO.builder()
            .id(5)
            .tagsList(new ArrayList<>(Arrays.asList(1,3,4)))
            .number("LNDR-0005")
            .name("Остаток на складах шоурум выводить отдельно (ТЗ)")
            .timeLimit(60*1000)
            .build();
        tasksList.add(task);

        task = TaskPOJO.builder()
            .id(6)
            .tagsList(new ArrayList<>(Arrays.asList()))
            .number("LNDR-0006")
            .name("Остаток на складах шоурум выводить отдельно (ТЗ)")
            .timeLimit(60*1000)
            .build();
        tasksList.add(task);
    }
    // @formatter:on

    private List<TaskManager> getManagerList() {
        return tasksList.stream().map(TaskManager::new).collect(Collectors.toList());
    }

    @Override
    public List<TaskManager> getAll() {
        return getManagerList();
    }

    @Override
    public List<TaskManager> getByTagId(int tagId) {
        if (tagId == 0) {
            return getAll();
        }
        return getManagerList().stream().filter(task -> task.getTagsList().contains(tagId)).collect(Collectors.toList());
    }

    @Override
    public Optional<TaskManager> getById(int taskId) {
        return getManagerList().stream().filter(task -> task.getId() == taskId).findFirst();
    }

    @Override
    public List<TaskManager> getWithExclude(int taskId) {
        return getManagerList().stream().filter(task -> task.getId() != taskId).collect(Collectors.toList());
    }

    @Override
    public TaskManager add(String number, String name, int tagId) {
        TaskPOJO taskPOJO = TaskPOJO.builder()
                                    .id(tasksList.size() + 1)
                                    .tagsList(Collections.singletonList(tagId))
                                    .number(number)
                                    .name(name)
                                    .build();
        tasksList.add(taskPOJO);
        return new TaskManager(taskPOJO);
    }

    @Override
    public TaskManager update(int taskId, TaskVi taskVi) {
        TaskManager task = getById(taskId).orElseThrow();
        task.fillFrom(taskVi);
        return task;
    }
}
