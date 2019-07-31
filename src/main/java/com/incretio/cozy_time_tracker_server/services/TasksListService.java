package com.incretio.cozy_time_tracker_server.services;

import com.incretio.cozy_time_tracker_server.dao.TagsDAO;
import com.incretio.cozy_time_tracker_server.dao.TasksDAO;
import com.incretio.cozy_time_tracker_server.exception.NoSuchTaskException;
import com.incretio.cozy_time_tracker_server.helpers.TextSplitOnNumberAndName;
import com.incretio.cozy_time_tracker_server.model.ex.Task;
import com.incretio.cozy_time_tracker_server.model.vi.TaskVi;
import com.incretio.cozy_time_tracker_server.model.vo.TagVo;
import com.incretio.cozy_time_tracker_server.model.vo.TaskVo;
import com.incretio.cozy_time_tracker_server.model.vo.helper.ConvertVo;
import com.incretio.cozy_time_tracker_server.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path ("v.1")
public class TasksListService {
    private static final TagsDAO tagsDAO = new TagsDAO();
    private static final TasksDAO tasksDAO = new TasksDAO();
    private static final ConvertVo convertVo = new ConvertVo();

    @GET
    @Path ("test")
    @Produces (MediaType.APPLICATION_JSON)
    public String getTasksList() {
        return "O.K.";
    }

    @GET
    @Path ("tags_list")
    @Produces (MediaType.APPLICATION_JSON)
    public List<TagVo> tagList(@Context final HttpServletRequest req, @Context final HttpServletResponse res) {
        //        res.setHeader("Access-Control-Allow-Origin", "*");
        return convertVo.toVo(tagsDAO.getTagsList());
    }

    @GET
    @Path ("tasks_list/{tagId}")
    @Produces (MediaType.APPLICATION_JSON)
    public List<TaskVo> tasksList(@Context final HttpServletRequest req, @Context final HttpServletResponse res,
                                  @PathParam ("tagId") int tagId) {
        res.setHeader("Access-Control-Allow-Origin", "*");
        return convertVo.toVo(tasksDAO.getTasksList(tagId));
    }

    @POST
    @Path ("task/toggle/{taskId}/tag/{tagId}")
    @Produces (MediaType.APPLICATION_JSON)
    public List<TaskVo> taskToggle(@Context final HttpServletRequest req, @Context final HttpServletResponse res,
                                   @PathParam ("taskId") int taskId, @PathParam ("tagId") int tagId) {
        res.setHeader("Access-Control-Allow-Origin", "*");
        tasksDAO.getTasksWithExclude(taskId)
                .forEach(Task::stop);
        tasksDAO.getTask(taskId)
                .orElseThrow(NoSuchTaskException::new)
                .toggle();
        return convertVo.toVo(tasksDAO.getTasksList(tagId));
    }

    @POST
    @Path ("task/new")
    @Consumes (MediaType.APPLICATION_FORM_URLENCODED)
    @Produces (MediaType.APPLICATION_JSON)
    public List<TaskVo> addNewTask(@Context final HttpServletRequest req, @Context final HttpServletResponse res,
                                   @FormParam ("taskName") String taskName, @FormParam ("tagId") int tagId) {
        res.setHeader("Access-Control-Allow-Origin", "*");
        if (StringUtil.isNotBlank(taskName)) {
            TextSplitOnNumberAndName textSplitOnNumberAndName = new TextSplitOnNumberAndName(taskName);
            tasksDAO.addTask(textSplitOnNumberAndName.getNumber(), textSplitOnNumberAndName.getName(), tagId);
        }
        return convertVo.toVo(tasksDAO.getTasksList(tagId));
    }

    @POST
    @Path ("task/update/{taskId}")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public TaskVo updateTask(@Context final HttpServletRequest req, @Context final HttpServletResponse res,
                             @PathParam ("taskId") int taskId, TaskVi taskVi) {
        Task task = tasksDAO.update(taskId, taskVi);
        return convertVo.toVo(task);
    }

}
