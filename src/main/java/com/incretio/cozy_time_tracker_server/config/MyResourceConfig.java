package com.incretio.cozy_time_tracker_server.config;

import org.glassfish.jersey.server.ResourceConfig;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;

@ApplicationPath ("api")
public class MyResourceConfig extends ResourceConfig {
    public MyResourceConfig(@Context ServletContext servletContext) {
        System.out.println("MyResourceConfig initializing...");
        packages(true, "com.incretio.cozy_time_tracker_server");
        register(new MyBinder());
    }
}
