package com.incretio.cozy_time_tracker_server.repository.memory;

import com.incretio.cozy_time_tracker_server.model.local.timeinterval.TimeIntervalManager;
import com.incretio.cozy_time_tracker_server.model.local.timeinterval.TimeIntervalPOJO;
import com.incretio.cozy_time_tracker_server.repository.TimeIntervalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemoryTimeIntervalRepository implements TimeIntervalRepository {
    private final SharedMemoryData sharedMemoryData;

    @Autowired
    public MemoryTimeIntervalRepository(SharedMemoryData sharedMemoryData) {
        this.sharedMemoryData = sharedMemoryData;
    }

    @Override
    public List<TimeIntervalManager> getAll() {
        return sharedMemoryData.getTimeIntervalList().stream().map(TimeIntervalManager::new).collect(Collectors.toList());
    }
}
