package com.incretio.cozy_time_tracker_server.web;

import com.incretio.cozy_time_tracker_server.model.vo.TagVo;
import com.incretio.cozy_time_tracker_server.service.TagService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Path ("v.1")
public class TagController {

    private static final Logger log = getLogger(TagController.class);

    @Inject
    private TagService tagService;

    @GET
    @Path ("tags_list")
    @Produces (MediaType.APPLICATION_JSON)
    public List<TagVo> getAll(@Context final HttpServletRequest req, @Context final HttpServletResponse res) {
        return tagService.getAll();
    }

}
