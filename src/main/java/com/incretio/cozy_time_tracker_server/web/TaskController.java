package com.incretio.cozy_time_tracker_server.web;

import com.incretio.cozy_time_tracker_server.model.vi.TaskVi;
import com.incretio.cozy_time_tracker_server.model.vo.TaskVo;
import com.incretio.cozy_time_tracker_server.service.TaskService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("task")
public class TaskController {

    private static final Logger log = getLogger(TaskController.class);

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping (
        value = "/test",
        produces = MediaType.APPLICATION_JSON)
    public String test() {
        log.info("test O..K..");
        return "success_test";
    }

    @GetMapping (
        value = "bytag/{tagId}",
        produces = MediaType.APPLICATION_JSON)
    public List<TaskVo> getByTagId(@Context final HttpServletRequest req,
                                   @Context final HttpServletResponse res,
                                   @PathVariable ("tagId") int tagId) {
        return taskService.getByTagId(tagId);
    }

    @GetMapping (
        value = "{taskId}",
        produces = MediaType.APPLICATION_JSON)
    public TaskVo getByTaskId(@Context final HttpServletRequest req,
                                   @Context final HttpServletResponse res,
                                   @PathVariable ("taskId") int taskId) {
        return taskService.getById(taskId);
    }

    @PostMapping (
        value = "toggle/{taskId}/tag/{tagId}",
        produces = MediaType.APPLICATION_JSON)
    public List<TaskVo> toggleState(@Context final HttpServletRequest req,
                                    @Context final HttpServletResponse res,
                                    @PathVariable ("taskId") int taskId,
                                    @PathVariable ("tagId") int tagId) {
        return taskService.toggleState(taskId, tagId);
    }

    @PostMapping (
        value = "new",
        consumes = MediaType.APPLICATION_FORM_URLENCODED,
        produces = MediaType.APPLICATION_JSON)
    public List<TaskVo> add(@Context final HttpServletRequest req,
                            @Context final HttpServletResponse res,
                            @FormParam ("taskName") String taskName,
                            @FormParam ("tagId") int tagId) {
        return taskService.add(taskName, tagId);
    }

    @PostMapping (
        value = "update/{taskId}",
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON)
    public TaskVo update(@Context final HttpServletRequest req,
                         @Context final HttpServletResponse res,
                         @PathVariable ("taskId") int taskId,
                         @RequestBody TaskVi taskVi) {
        return taskService.update(taskId, taskVi);
    }

}
