package com.incretio.cozy_time_tracker_server.utils.millisecondsUtil;

import com.incretio.cozy_time_tracker_server.utils.MillisecondsUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith (Parameterized.class)
public class MillisecondsUtil_MsToRemainderOfMinutes_Test {
    private final long ms;
    private final long expected;

    public MillisecondsUtil_MsToRemainderOfMinutes_Test(long ms, long expected) {
        this.ms = ms;
        this.expected = expected;
    }

    @Parameterized.Parameters (name = "{index}: ms = {0}, minutes = {1}")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(
            // @formatter:off
            new Object[][] {
                { 0, 0 },
                { 59 * 1000, 0 },
                { 60 * 1000, 1 },
                { 59 * 60 * 1000, 59 },
                { 60 * 60 * 1000, 0 },
                { 61 * 60 * 1000, 1 },
                { 119 * 60 * 1000, 59 },
                { 120 * 60 * 1000, 0 },
                { 121 * 60 * 1000, 1 },
            });
    }

    @Test
    public void msToRemainderOfMinutes_test(){
        // when
        long actual = MillisecondsUtil.msToRemainderOfMinutes(ms);
        // then
        Assert.assertEquals(expected, actual);
    }
}