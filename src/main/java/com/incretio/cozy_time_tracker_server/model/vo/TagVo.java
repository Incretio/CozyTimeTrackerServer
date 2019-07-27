package com.incretio.cozy_time_tracker_server.model.vo;

import com.incretio.cozy_time_tracker_server.model.ex.Tag;

public class TagVo extends BaseVo {
    private final Tag tag;

    public TagVo(Tag tag) {
        this.tag = tag;
    }

    public int getId() {
        return tag.getId();
    }

    public String getName() {
        return tag.getName();
    }
}
