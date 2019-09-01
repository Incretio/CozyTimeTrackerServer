package com.incretio.cozy_time_tracker_server.config;

import com.incretio.cozy_time_tracker_server.dao.TagsDAO;
import com.incretio.cozy_time_tracker_server.dao.TasksDAO;
import com.incretio.cozy_time_tracker_server.model.vo.helper.ConvertVo;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class MyBinder extends AbstractBinder {
    @Override
    protected void configure() {
        System.out.println("MyBinder initializing...");
        bindAsContract(ApplicationProperties.class).in(Singleton.class);
        bindAsContract(TagsDAO.class).in(Singleton.class);
        bindAsContract(TasksDAO.class).in(Singleton.class);
        bindAsContract(ConvertVo.class).in(Singleton.class);
    }
}