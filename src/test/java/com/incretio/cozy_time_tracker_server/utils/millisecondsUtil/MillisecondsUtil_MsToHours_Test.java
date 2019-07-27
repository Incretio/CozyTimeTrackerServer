package com.incretio.cozy_time_tracker_server.utils.millisecondsUtil;

import com.incretio.cozy_time_tracker_server.utils.MillisecondsUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith (Parameterized.class)
public class MillisecondsUtil_MsToHours_Test {
    private final long ms;
    private final long expected;

    public MillisecondsUtil_MsToHours_Test(long ms, long expected) {
        this.ms = ms;
        this.expected = expected;
    }

    @Parameterized.Parameters (name = "{index}: ms = {0}, hours = {1}")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(
            // @formatter:off
            new Object[][] {
                { 0, 0 },
                { 60 * 1000, 0 },
                { 59 * 60 * 1000, 0 },
                { 60 * 60 * 1000, 1 },
                { 24 * 60 * 60 * 1000, 24 },
                { 99 * 60 * 60 * 1000, 99 },
                { 999L * 60 * 60 * 1000, 999 },
                { 999L * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000, 999 },
            });
    }

    @Test
    public void msToHours_test(){
        // when
        long actual = MillisecondsUtil.msToHours(ms);
        // then
        Assert.assertEquals(expected, actual);
    }
}