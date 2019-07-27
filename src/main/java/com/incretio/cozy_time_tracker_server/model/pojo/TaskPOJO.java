package com.incretio.cozy_time_tracker_server.model.pojo;

import com.incretio.cozy_time_tracker_server.model.ex.BaseModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class TaskPOJO extends BaseModel {
    @Getter int id;
    @Getter int tagId;
    @Getter String number;
    @Getter String name;
    @Getter String description;
    @Getter @Setter (AccessLevel.PROTECTED) long timeLeftToday;
    @Getter @Setter (AccessLevel.PROTECTED) long timeLeftAll;
    @Getter long timeLimit;
    @Getter @Setter (AccessLevel.PROTECTED) TaskStatus taskStatus;
}
