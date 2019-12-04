package com.incretio.cozy_time_tracker_server.service;

import com.incretio.cozy_time_tracker_server.exception.NoSuchTaskException;
import com.incretio.cozy_time_tracker_server.helpers.TextSplitOnNumberAndName;
import com.incretio.cozy_time_tracker_server.model.ex.Task;
import com.incretio.cozy_time_tracker_server.model.vi.TaskVi;
import com.incretio.cozy_time_tracker_server.model.vo.TaskVo;
import com.incretio.cozy_time_tracker_server.model.vo.helper.ConvertVo;
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

    public List<TaskVo> toggleState(int taskId, int tagId) {
        taskRepository.getWithExclude(taskId).forEach(Task::stop);
        taskRepository.getById(taskId).orElseThrow(NoSuchTaskException::new).toggle();
        return ConvertVo.toVo(taskRepository.getByTagId(tagId));
    }

    public List<TaskVo> add(String taskName, int tagId) {
        if (StringUtil.isNotBlank(taskName)) {
            TextSplitOnNumberAndName textSplitOnNumberAndName = new TextSplitOnNumberAndName(taskName);
            taskRepository.add(textSplitOnNumberAndName.getNumber(), textSplitOnNumberAndName.getName(), tagId);
        }
        return ConvertVo.toVo(taskRepository.getByTagId(tagId));
    }

    public TaskVo updateTask(int taskId, TaskVi taskVi) {
        Task task = taskRepository.update(taskId, taskVi);
        return ConvertVo.toVo(task);
    }

}
