package com.incretio.cozy_time_tracker_server.repository;

import com.incretio.cozy_time_tracker_server.model.ex.Task;
import com.incretio.cozy_time_tracker_server.model.vi.TaskVi;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> getAll();
    List<Task> getByTagId(int tagId);
    Optional<Task> getById(int taskId);
    List<Task> getWithExclude(int taskId);
    Task add(String number, String name, int tagId);
    Task update(int taskId, TaskVi taskVi);

}
