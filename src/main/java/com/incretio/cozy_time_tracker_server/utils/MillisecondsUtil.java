package com.incretio.cozy_time_tracker_server.utils;

public class MillisecondsUtil {
    public static long msToHours(long ms) {
        return ms / 60 / 60 / 1000;
    }

    public static long msToMinutes(long ms) {
        return ms / 60 / 1000;
    }

    public static long msToSeconds(long ms) {
        return ms / 1000;
    }

    public static long msToRemainderOfMinutes(long ms) {
        return msToMinutes(ms) % 60;
    }

    public static long msToRemainderOfSeconds(long ms) {
        return msToSeconds(ms) % 60;
    }

}
