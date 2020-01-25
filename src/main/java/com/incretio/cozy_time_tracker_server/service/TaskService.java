package com.incretio.cozy_time_tracker_server.service;

import com.incretio.cozy_time_tracker_server.exception.TaskNotFoundException;
import com.incretio.cozy_time_tracker_server.helpers.TextSplitOnNumberAndName;
import com.incretio.cozy_time_tracker_server.model.local.task.TaskManager;
import com.incretio.cozy_time_tracker_server.model.remote.vi.TaskVi;
import com.incretio.cozy_time_tracker_server.model.remote.vo.TaskVo;
import com.incretio.cozy_time_tracker_server.model.remote.vo.helper.ConvertVo;
import com.incretio.cozy_time_tracker_server.repository.TaskRepository;
import com.incretio.cozy_time_tracker_server.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskVo> getByTagId(int tagId) {
        return ConvertVo.toVo(taskRepository.getByTagId(tagId));
    }

    public TaskVo getById(int taskId) {
        return taskRepository.getById(taskId)
                             .map(TaskManager::toVo)
                             .orElseThrow(TaskNotFoundException::new);
    }

    public List<TaskVo> toggleState(int taskId, int tagId) {
        taskRepository.getWithExclude(taskId).forEach(TaskManager::stop);
        taskRepository.getById(taskId).orElseThrow(TaskNotFoundException::new).toggle();
        return ConvertVo.toVo(taskRepository.getByTagId(tagId));
    }

    public List<TaskVo> add(String taskName, int tagId) {
        if (StringUtil.isNotBlank(taskName)) {
            TextSplitOnNumberAndName textSplitOnNumberAndName = new TextSplitOnNumberAndName(taskName);
            taskRepository.add(textSplitOnNumberAndName.getNumber(), textSplitOnNumberAndName.getName(), tagId);
        }
        return ConvertVo.toVo(taskRepository.getByTagId(tagId));
    }

    public TaskVo update(int taskId, TaskVi taskVi) {
        TaskManager task = taskRepository.update(taskId, taskVi);
        return ConvertVo.toVo(task);
    }

}
