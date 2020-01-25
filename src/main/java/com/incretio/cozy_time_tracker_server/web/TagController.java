package com.incretio.cozy_time_tracker_server.web;

import com.incretio.cozy_time_tracker_server.model.remote.vo.TagVo;
import com.incretio.cozy_time_tracker_server.service.TagService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("tag")
public class TagController {

    private static final Logger log = getLogger(TagController.class);

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping (
        value = "list",
        produces = MediaType.APPLICATION_JSON)
    public List<TagVo> getAll(@Context final HttpServletRequest req,
                              @Context final HttpServletResponse res) {
        return tagService.getAll();
    }

}

