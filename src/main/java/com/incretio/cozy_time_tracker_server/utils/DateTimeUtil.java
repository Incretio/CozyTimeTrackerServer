package com.incretio.cozy_time_tracker_server.utils;

import java.time.Instant;

public class DateTimeUtil {
    public static String msToTimeString(long ms) {
        long hours = MillisecondsUtil.msToHours(ms);
        long minutes = MillisecondsUtil.msToRemainderOfMinutes(ms);
        long seconds = MillisecondsUtil.msToRemainderOfSeconds(ms);
        String template = "%02d:%02d:%02d";
        return String.format(template, hours, minutes, seconds);
    }

    public static long nowInMs() {
        return Instant.now()
                      .toEpochMilli();
    }
}
