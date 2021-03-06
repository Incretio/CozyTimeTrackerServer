package com.incretio.cozy_time_tracker_server.repository;

import com.incretio.cozy_time_tracker_server.model.local.tag.TagManager;
import com.incretio.cozy_time_tracker_server.model.local.tag.TagPOJO;

import java.util.List;

public interface TagRepository {

    List<TagPOJO> getAll();

    List<TagManager> getAllInManager();

}
