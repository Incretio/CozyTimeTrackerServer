package com.incretio.cozy_time_tracker_server.model.pojo;

import com.incretio.cozy_time_tracker_server.model.ex.BaseModel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class TaskPOJO extends BaseModel {
    @Getter int id;
    @Getter @Setter List<Integer> tagsList = new ArrayList<>();
    @Getter @Setter String number;
    @Getter @Setter String name;
    @Getter @Setter String description;
    @Getter @Setter (AccessLevel.PROTECTED) long timeLeftToday;
    @Getter @Setter (AccessLevel.PROTECTED) long timeLeftAll;
    @Getter long timeLimit;
    @Getter @Setter (AccessLevel.PROTECTED) TaskStatus taskStatus;
}
