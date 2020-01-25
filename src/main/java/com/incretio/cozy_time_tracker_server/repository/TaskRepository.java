package com.incretio.cozy_time_tracker_server.repository;

import com.incretio.cozy_time_tracker_server.model.local.task.TaskManager;
import com.incretio.cozy_time_tracker_server.model.remote.vi.TaskVi;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<TaskManager> getAll();
    List<TaskManager> getByTagId(int tagId);
    Optional<TaskManager> getById(int taskId);
    List<TaskManager> getWithExclude(int taskId);
    TaskManager add(String number, String name, int tagId);
    TaskManager update(int taskId, TaskVi taskVi);

}
