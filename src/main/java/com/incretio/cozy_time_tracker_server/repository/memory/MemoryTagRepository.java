package com.incretio.cozy_time_tracker_server.repository.memory;

import com.incretio.cozy_time_tracker_server.model.local.tag.TagManager;
import com.incretio.cozy_time_tracker_server.model.local.tag.TagPOJO;
import com.incretio.cozy_time_tracker_server.repository.TagRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemoryTagRepository implements TagRepository {

    private List<TagPOJO> tagsList = new ArrayList<>();

    {
        tagsList.add(new TagPOJO(0, "Все"));
        tagsList.add(new TagPOJO(1, "Спринт"));
        tagsList.add(new TagPOJO(2, "Lunda.ru"));
        tagsList.add(new TagPOJO(3, "Lunda.se"));
        tagsList.add(new TagPOJO(4, "Purchase"));
    }

    @Override
    public List<TagPOJO> getAll() {
        return tagsList;
    }

    @Override
    public List<TagManager> getAllInManager() {
        return tagsList.stream().map(TagManager::new).collect(Collectors.toList());
    }

}
