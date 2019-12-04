package com.incretio.cozy_time_tracker_server.service;

import com.incretio.cozy_time_tracker_server.repository.TagsRepository;
import com.incretio.cozy_time_tracker_server.repository.TasksRepository;
import com.incretio.cozy_time_tracker_server.exception.NoSuchTaskException;
import com.incretio.cozy_time_tracker_server.helpers.TextSplitOnNumberAndName;
import com.incretio.cozy_time_tracker_server.model.ex.Task;
import com.incretio.cozy_time_tracker_server.model.vi.TaskVi;
import com.incretio.cozy_time_tracker_server.model.vo.TagVo;
import com.incretio.cozy_time_tracker_server.model.vo.TaskVo;
import com.incretio.cozy_time_tracker_server.model.vo.helper.ConvertVo;
import com.incretio.cozy_time_tracker_server.utils.StringUtil;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Path ("v.1")
public class TasksListService {
    private static final Logger log = getLogger(TasksListService.class);

    @Inject
    private TagsRepository tagsRepository;
    @Inject
    private TasksRepository tasksRepository;
    @Inject
    private ConvertVo convertVo;

    @GET
    @Path ("test")
    @Produces (MediaType.APPLICATION_JSON)
    public String getTasksList() {
        log.info("test O.K.");
        return "test O.K.";
    }

    @GET
    @Path ("tags_list")
    @Produces (MediaType.APPLICATION_JSON)
    public List<TagVo> tagList(@Context final HttpServletRequest req, @Context final HttpServletResponse res) {
        return convertVo.toVo(tagsRepository.getAll());
    }

    @GET
    @Path ("tasks_list/{tagId}")
    @Produces (MediaType.APPLICATION_JSON)
    public List<TaskVo> tasksList(@Context final HttpServletRequest req, @Context final HttpServletResponse res,
                                  @PathParam ("tagId") int tagId) {
        return convertVo.toVo(tasksRepository.getByTagId(tagId));
    }

    @POST
    @Path ("task/toggle/{taskId}/tag/{tagId}")
    @Produces (MediaType.APPLICATION_JSON)
    public List<TaskVo> taskToggle(@Context final HttpServletRequest req, @Context final HttpServletResponse res,
                                   @PathParam ("taskId") int taskId, @PathParam ("tagId") int tagId) {
        tasksRepository.getWithExclude(taskId).forEach(Task::stop);
        tasksRepository.getById(taskId).orElseThrow(NoSuchTaskException::new).toggle();
        return convertVo.toVo(tasksRepository.getByTagId(tagId));
    }

    @POST
    @Path ("task/new")
    @Consumes (MediaType.APPLICATION_FORM_URLENCODED)
    @Produces (MediaType.APPLICATION_JSON)
    public List<TaskVo> addNewTask(@Context final HttpServletRequest req, @Context final HttpServletResponse res,
                                   @FormParam ("taskName") String taskName, @FormParam ("tagId") int tagId) {
        if (StringUtil.isNotBlank(taskName)) {
            TextSplitOnNumberAndName textSplitOnNumberAndName = new TextSplitOnNumberAndName(taskName);
            tasksRepository.add(textSplitOnNumberAndName.getNumber(), textSplitOnNumberAndName.getName(), tagId);
        }
        return convertVo.toVo(tasksRepository.getByTagId(tagId));
    }

    @POST
    @Path ("task/update/{taskId}")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public TaskVo updateTask(@Context final HttpServletRequest req, @Context final HttpServletResponse res,
                             @PathParam ("taskId") int taskId, TaskVi taskVi) {
        Task task = tasksRepository.update(taskId, taskVi);
        return convertVo.toVo(task);
    }

}
