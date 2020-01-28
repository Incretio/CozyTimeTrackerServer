package com.incretio.cozy_time_tracker_server.repository.memory;

import com.incretio.cozy_time_tracker_server.model.local.task.TaskManager;
import com.incretio.cozy_time_tracker_server.model.local.task.TaskPOJO;
import com.incretio.cozy_time_tracker_server.model.remote.vi.TaskVi;
import com.incretio.cozy_time_tracker_server.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryTaskRepository implements TaskRepository {
    private SharedMemoryData sharedMemoryData;

    @Autowired
    public MemoryTaskRepository(SharedMemoryData sharedMemoryData) {
        this.sharedMemoryData = sharedMemoryData;
    }

    private List<TaskManager> getManagerList() {
        return sharedMemoryData.getTasksList().stream().map(TaskManager::new).collect(Collectors.toList());
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
                                    .id(sharedMemoryData.getTasksList().size() + 1)
                                    .tagsList(Collections.singletonList(tagId))
                                    .number(number)
                                    .name(name)
                                    .build();
        sharedMemoryData.getTasksList().add(taskPOJO);
        return new TaskManager(taskPOJO);
    }

    @Override
    public TaskManager update(int taskId, TaskVi taskVi) {
        TaskManager task = getById(taskId).orElseThrow();
        task.fillFrom(taskVi);
        return task;
    }
}
