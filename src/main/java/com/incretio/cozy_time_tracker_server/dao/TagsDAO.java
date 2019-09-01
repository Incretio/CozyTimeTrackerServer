package com.incretio.cozy_time_tracker_server.dao;

import com.incretio.cozy_time_tracker_server.config.ApplicationProperties;
import com.incretio.cozy_time_tracker_server.model.ex.Tag;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TagsDAO {
    private String siteHome;

    public String getSiteHome() {
        return siteHome;
    }

    @Inject
    public TagsDAO(ApplicationProperties applicationProperties) {
        this.siteHome = applicationProperties.getProperty("site.home");
    }

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
