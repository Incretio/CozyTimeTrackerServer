package com.incretio.cozy_time_tracker_server.repository.memory;

import com.incretio.cozy_time_tracker_server.model.ex.Tag;
import com.incretio.cozy_time_tracker_server.repository.TagRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryTagRepository implements TagRepository {

    private List<Tag> tagsList = new ArrayList<>();

    {
        tagsList.add(new Tag(0, "Все"));
        tagsList.add(new Tag(1, "Спринт"));
        tagsList.add(new Tag(2, "Lunda.ru"));
        tagsList.add(new Tag(3, "Lunda.se"));
        tagsList.add(new Tag(4, "Purchase"));
    }

    @Override
    public List<Tag> getAll() {
        return tagsList;
    }

}
