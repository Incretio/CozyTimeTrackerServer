package com.incretio.cozy_time_tracker_server.repository;

import com.incretio.cozy_time_tracker_server.model.ex.Tag;

import java.util.List;

public interface TagsRepository {

    List<Tag> getAll();

}
