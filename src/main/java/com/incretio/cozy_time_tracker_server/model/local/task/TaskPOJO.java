package com.incretio.cozy_time_tracker_server.model.local.task;

import com.incretio.cozy_time_tracker_server.model.local.BaseModel;
import com.incretio.cozy_time_tracker_server.model.local.taskstatus.TaskStatus;
import com.incretio.cozy_time_tracker_server.model.local.timeinterval.TimeIntervalPOJO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskPOJO extends BaseModel {
    @Getter int id;
    @Builder.Default
    @Getter @Setter List<Integer> tagsList = new ArrayList<>();
    @Builder.Default
    @Getter @Setter String number = "";
    @Builder.Default
    @Getter @Setter String name = "";
    @Builder.Default
    @Getter @Setter String description = "";
    @Getter @Setter (AccessLevel.PROTECTED) long timeLeftToday;
    @Getter @Setter (AccessLevel.PROTECTED) long timeLeftAll;
    @Getter long timeLimit;
    @Builder.Default
    @Getter @Setter (AccessLevel.PROTECTED) TaskStatus taskStatus = TaskStatus.STOPPED;
    @Builder.Default
    @Getter (AccessLevel.PROTECTED) List<TimeIntervalPOJO> timeIntervalPOJOList = new ArrayList<>();

//    public TaskPOJO(TaskPOJO fromObject) {
//        this.id = fromObject.id;
//        this.tagsList = fromObject.tagsList;
//        this.number = fromObject.number;
//        this.name = fromObject.name;
//        this.description = fromObject.description;
//        this.timeLeftToday = fromObject.timeLeftToday;
//        this.timeLeftAll = fromObject.timeLeftAll;
//        this.timeLimit = fromObject.timeLimit;
//        this.taskStatus = fromObject.taskStatus;
//        this.timeIntervalPOJOList = fromObject.timeIntervalPOJOList;
//    }
}
