package com.incretio.cozy_time_tracker_server.model.local.timeinterval;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class TimeIntervalPOJO {

    @Getter @Setter private Date start;
    @Getter @Setter private Date stop;
    @Getter @Setter private String message = "";

}
