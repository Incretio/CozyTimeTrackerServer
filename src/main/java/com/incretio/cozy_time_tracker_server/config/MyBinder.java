package com.incretio.cozy_time_tracker_server.config;

import com.incretio.cozy_time_tracker_server.repository.memory.MemoryTagRepository;
import com.incretio.cozy_time_tracker_server.repository.memory.MemoryTaskRepository;
import com.incretio.cozy_time_tracker_server.model.vo.helper.ConvertVo;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class MyBinder extends AbstractBinder {
    @Override
    protected void configure() {
        System.out.println("MyBinder initializing...");
        bindAsContract(ApplicationProperties.class).in(Singleton.class);
        bindAsContract(MemoryTagRepository.class).in(Singleton.class);
        bindAsContract(MemoryTaskRepository.class).in(Singleton.class);
        bindAsContract(ConvertVo.class).in(Singleton.class);
    }
}