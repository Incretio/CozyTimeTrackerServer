package com.incretio.cozy_time_tracker_server.config;

import com.incretio.cozy_time_tracker_server.dao.TagsDAO;
import com.incretio.cozy_time_tracker_server.dao.TasksDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource ("/WEB-INF/config/conf.properties")
public class AppConfiguration {
    @Value ("${test}") private String test;

    public AppConfiguration() {
        System.out.println("appConfiguration running...");
    }

    @Bean
    TagsDAO tagsDAO() {
        TagsDAO tagsDAO = new TagsDAO();
        tagsDAO.setVersion(test);
        return tagsDAO;
    }

    @Bean
    TasksDAO tasksDAO() {
        return new TasksDAO();
    }

}
