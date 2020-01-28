package com.incretio.cozy_time_tracker_server.repository.memory;

import com.incretio.cozy_time_tracker_server.model.local.tag.TagManager;
import com.incretio.cozy_time_tracker_server.model.local.tag.TagPOJO;
import com.incretio.cozy_time_tracker_server.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemoryTagRepository implements TagRepository {
    private final SharedMemoryData sharedMemoryData;

    @Autowired
    public MemoryTagRepository(SharedMemoryData sharedMemoryData) {
        this.sharedMemoryData = sharedMemoryData;
    }

    @Override
    public List<TagPOJO> getAll() {
        return sharedMemoryData.getTagsList();
    }

    @Override
    public List<TagManager> getAllInManager() {
        return sharedMemoryData.getTagsList().stream().map(TagManager::new).collect(Collectors.toList());
    }

}
