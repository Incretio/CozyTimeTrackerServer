package com.incretio.cozy_time_tracker_server.config;

import com.incretio.cozy_time_tracker_server.repository.memory.MemoryTagsRepository;
import com.incretio.cozy_time_tracker_server.repository.memory.MemoryTasksRepository;
import com.incretio.cozy_time_tracker_server.model.vo.helper.ConvertVo;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class MyBinder extends AbstractBinder {
    @Override
    protected void configure() {
        System.out.println("MyBinder initializing...");
        bindAsContract(ApplicationProperties.class).in(Singleton.class);
        bindAsContract(MemoryTagsRepository.class).in(Singleton.class);
        bindAsContract(MemoryTasksRepository.class).in(Singleton.class);
        bindAsContract(ConvertVo.class).in(Singleton.class);
    }
}