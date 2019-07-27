package com.incretio.cozy_time_tracker_server.model.ex;

import com.incretio.cozy_time_tracker_server.model.pojo.TagPOJO;
import com.incretio.cozy_time_tracker_server.model.vo.TagVo;
import com.incretio.cozy_time_tracker_server.model.vo.helper.ToVoConvertable;

public class Tag extends TagPOJO implements ToVoConvertable<TagVo> {
    public Tag(int id, String name) {
        super(id, name);
    }

    @Override
    public TagVo toVo() {
        return new TagVo(this);
    }
}
