package com.incretio.cozy_time_tracker_server.model.local.tag;

import com.incretio.cozy_time_tracker_server.model.local.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder(toBuilder = true)
public class TagPOJO extends BaseModel {
    @Getter protected int id;
    @Getter protected String name;

//    public TagPOJO(TagPOJO tagPOJO) {
//        this.id = tagPOJO.id;
//        this.name = tagPOJO.name;
//    }
}




