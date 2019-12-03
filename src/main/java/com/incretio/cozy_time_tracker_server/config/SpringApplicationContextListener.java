package com.incretio.cozy_time_tracker_server.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SpringApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        sce.getServletContext().setAttribute("applicationContext", applicationContext);
    }

}
