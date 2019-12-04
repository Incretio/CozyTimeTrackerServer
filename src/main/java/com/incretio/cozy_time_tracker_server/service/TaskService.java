package com.incretio.cozy_time_tracker_server.service;

import com.incretio.cozy_time_tracker_server.exception.NoSuchTaskException;
import com.incretio.cozy_time_tracker_server.helpers.TextSplitOnNumberAndName;
import com.incretio.cozy_time_tracker_server.model.ex.Task;
import com.incretio.cozy_time_tracker_server.model.vi.TaskVi;
import com.incretio.cozy_time_tracker_server.model.vo.TaskVo;
import com.incretio.cozy_time_tracker_server.model.vo.helper.ConvertVo;
import com.incretio.cozy_time_tracker_server.repository.TaskRepository;
import com.incretio.cozy_time_tracker_server.utils.StringUtil;

import javax.inject.Inject;
import java.util.List;

public class TaskService {

    @Inject
    private TaskRepository tasksRepository;
    @Inject
    private ConvertVo convertVo;

    public List<TaskVo> getByTagId(int tagId) {
        return convertVo.toVo(tasksRepository.getByTagId(tagId));
    }

    public List<TaskVo> toggleState(int taskId, int tagId) {
        tasksRepository.getWithExclude(taskId).forEach(Task::stop);
        tasksRepository.getById(taskId).orElseThrow(NoSuchTaskException::new).toggle();
        return convertVo.toVo(tasksRepository.getByTagId(tagId));
    }

    public List<TaskVo> add(String taskName, int tagId) {
        if (StringUtil.isNotBlank(taskName)) {
            TextSplitOnNumberAndName textSplitOnNumberAndName = new TextSplitOnNumberAndName(taskName);
            tasksRepository.add(textSplitOnNumberAndName.getNumber(), textSplitOnNumberAndName.getName(), tagId);
        }
        return convertVo.toVo(tasksRepository.getByTagId(tagId));
    }

    public TaskVo updateTask(int taskId, TaskVi taskVi) {
        Task task = tasksRepository.update(taskId, taskVi);
        return convertVo.toVo(task);
    }

}
