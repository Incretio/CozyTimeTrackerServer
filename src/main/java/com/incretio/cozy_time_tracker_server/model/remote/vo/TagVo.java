package com.incretio.cozy_time_tracker_server.model.remote.vo;

import com.incretio.cozy_time_tracker_server.model.local.tag.TagManager;

public class TagVo extends BaseVo {
    private final TagManager tag;

    public TagVo(TagManager tag) {
        this.tag = tag;
    }

    public int getId() {
        return tag.getId();
    }

    public String getName() {
        return tag.getName();
    }
}
