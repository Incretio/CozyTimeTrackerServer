package com.incretio.cozy_time_tracker_server.model.vi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class TaskVi {
    @Getter @Setter String number;
    @Getter @Setter String name;
    @Getter @Setter List<Integer> tagsList;
}
