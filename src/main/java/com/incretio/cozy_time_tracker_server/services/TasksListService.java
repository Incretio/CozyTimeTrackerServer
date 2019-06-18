package com.incretio.cozy_time_tracker_server.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path ("v.1")
public class TasksListService {

    @GET
    @Path ("test")
    @Produces (MediaType.APPLICATION_JSON)
    public String getTasksList() {
        return "O.K.";
    }

}
