package com.incretio.cozy_time_tracker_server.dao;

import com.incretio.cozy_time_tracker_server.model.ex.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagsDAO {
    private List<Tag> tagsList = new ArrayList<>();

    {
        tagsList.add(new Tag(0, "Все"));
        tagsList.add(new Tag(1, "Спринт"));
        tagsList.add(new Tag(2, "Lunda.ru"));
        tagsList.add(new Tag(3, "Lunda.se"));
        tagsList.add(new Tag(4, "Purchase"));
    }

    public List<Tag> getTagsList() {
        return tagsList;
    }

}
