package com.incretio.cozy_time_tracker_server.utils.dateTimeUtil;

import com.incretio.cozy_time_tracker_server.utils.DateTimeUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith (Parameterized.class)
public class DateTimeUtil_MsToTimeString_Test {
    private final long ms;
    private final String expected;

    public DateTimeUtil_MsToTimeString_Test(long ms, String expected) {
        this.ms = ms;
        this.expected = expected;
    }

    @Parameterized.Parameters (name = "{index}: ms = {0}, time = {1}")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(
            // @formatter:off
            new Object[][] {
                { 0, "00:00:00" },
                { 1000, "00:00:01" },
                { 59 * 1000, "00:00:59" },
                { 60 * 1000, "00:01:00" },
                { 59 * 60 * 1000, "00:59:00" },
                { 60 * 60 * 1000, "01:00:00" },
                { 24 * 60 * 60 * 1000, "24:00:00" },
                { 99 * 60 * 60 * 1000, "99:00:00" },
                { 999L * 60 * 60 * 1000, "999:00:00" },
                { 999L * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000, "999:59:59" },
            });
            // @formatter:on
    }

    @Test
    public void msToTimeString() {
        // when
        String actual = DateTimeUtil.msToTimeString(ms);
        // then
        Assert.assertEquals(expected, actual);
    }
}