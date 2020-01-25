package com.incretio.cozy_time_tracker_server.model.local.tag;

import com.incretio.cozy_time_tracker_server.model.remote.vo.TagVo;
import com.incretio.cozy_time_tracker_server.model.remote.vo.helper.ToVoConvertable;

public class TagManager implements ToVoConvertable<TagVo> {
    private final TagPOJO pojo;

    public TagManager(TagPOJO pojo) {
        this.pojo = pojo;
    }

    public TagPOJO getPojo() {
        return pojo;
    }

    @Override
    public TagVo toVo() {
        return new TagVo(this);
    }

    public int getId() {
        return pojo.getId();
    }

    public String getName() {
        return pojo.getName();
    }
}
