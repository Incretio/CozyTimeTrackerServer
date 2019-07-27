package com.incretio.cozy_time_tracker_server.utils.millisecondsUtil;

import com.incretio.cozy_time_tracker_server.utils.MillisecondsUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith (Parameterized.class)
public class MillisecondsUtil_MsToMinutes_Test {
    private final long ms;
    private final long expected;

    public MillisecondsUtil_MsToMinutes_Test(long ms, long expected) {
        this.ms = ms;
        this.expected = expected;
    }

    @Parameterized.Parameters (name = "{index}: ms = {0}, minutes = {1}")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(
            // @formatter:off
            new Object[][] {
                { 0, 0 },
                { 60 * 1000, 1 },
                { 59 * 60 * 1000, 59 },
                { 60 * 60 * 1000, 60 },
                { 24 * 60 * 60 * 1000, 24 * 60 },
                { 99 * 60 * 60 * 1000, 99 * 60 },
            });
    }

    @Test
    public void msToMinutes_test(){
        // when
        long actual = MillisecondsUtil.msToMinutes(ms);
        // then
        Assert.assertEquals(expected, actual);
    }
}