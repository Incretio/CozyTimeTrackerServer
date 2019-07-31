package com.incretio.cozy_time_tracker_server.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class MyApplication extends Application {
    public MyApplication() {
        System.out.println("MyApplication configure...");
    }
}
