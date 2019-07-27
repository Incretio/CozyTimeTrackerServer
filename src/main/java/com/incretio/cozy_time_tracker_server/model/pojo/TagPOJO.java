package com.incretio.cozy_time_tracker_server.model.pojo;

import com.incretio.cozy_time_tracker_server.model.ex.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TagPOJO extends BaseModel {
    @Getter protected int id;
    @Getter protected String name;
}




