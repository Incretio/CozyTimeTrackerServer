package com.incretio.cozy_time_tracker_server.web;

import com.incretio.cozy_time_tracker_server.model.vi.TaskVi;
import com.incretio.cozy_time_tracker_server.model.vo.TaskVo;
import com.incretio.cozy_time_tracker_server.service.TaskService;
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
public class TaskController {
    private static final Logger log = getLogger(TaskController.class);

    @Inject
    private TaskService taskService;

    @GET
    @Path ("test")
    @Produces (MediaType.APPLICATION_JSON)
    public String test() {
        log.info("test O.K.");
        return "test O.K.";
    }

    @GET
    @Path ("tasks_list/{tagId}")
    @Produces (MediaType.APPLICATION_JSON)
    public List<TaskVo> getByTagId(@Context final HttpServletRequest req, @Context final HttpServletResponse res,
                                   @PathParam ("tagId") int tagId) {
        return taskService.getByTagId(tagId);
    }

    @POST
    @Path ("task/toggle/{taskId}/tag/{tagId}")
    @Produces (MediaType.APPLICATION_JSON)
    public List<TaskVo> toggleState(@Context final HttpServletRequest req, @Context final HttpServletResponse res,
                                    @PathParam ("taskId") int taskId, @PathParam ("tagId") int tagId) {
        return taskService.toggleState(taskId, tagId);
    }

    @POST
    @Path ("task/new")
    @Consumes (MediaType.APPLICATION_FORM_URLENCODED)
    @Produces (MediaType.APPLICATION_JSON)
    public List<TaskVo> add(@Context final HttpServletRequest req, @Context final HttpServletResponse res,
                            @FormParam ("taskName") String taskName, @FormParam ("tagId") int tagId) {
        return taskService.add(taskName, tagId);
    }

    @POST
    @Path ("task/update/{taskId}")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public TaskVo update(@Context final HttpServletRequest req, @Context final HttpServletResponse res,
                         @PathParam ("taskId") int taskId, TaskVi taskVi) {
        return taskService.updateTask(taskId, taskVi);
    }

}
