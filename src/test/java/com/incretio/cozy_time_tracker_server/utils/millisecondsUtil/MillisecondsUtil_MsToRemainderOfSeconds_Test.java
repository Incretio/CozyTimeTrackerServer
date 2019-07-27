package com.incretio.cozy_time_tracker_server.utils.millisecondsUtil;

import com.incretio.cozy_time_tracker_server.utils.MillisecondsUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith (Parameterized.class)
public class MillisecondsUtil_MsToRemainderOfSeconds_Test {
    private final long ms;
    private final long expected;

    public MillisecondsUtil_MsToRemainderOfSeconds_Test(long ms, long expected) {
        this.ms = ms;
        this.expected = expected;
    }

    @Parameterized.Parameters (name = "{index}: ms = {0}, seconds = {1}")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(
            // @formatter:off
            new Object[][] {
                { 0, 0 },
                { 999, 0 },
                { 1000, 1 },
                { 59 * 1000, 59 },
                { 60 * 1000, 0 },
                { 61 * 1000, 1 },
                { 119 * 1000, 59 },
                { 120 * 1000, 0 },
            });
    }

    @Test
    public void msToRemainderOfSeconds_test(){
        // when
        long actual = MillisecondsUtil.msToRemainderOfSeconds(ms);
        // then
        Assert.assertEquals(expected, actual);
    }
}