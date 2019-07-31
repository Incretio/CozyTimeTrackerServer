package com.incretio.cozy_time_tracker_server.utils;

import java.util.Objects;

public class Condition {
    private Object value;
    private boolean executed = false;

    private Condition() {
        // noop
    }

    private Condition(Object value) {
        this.value = value;
    }

    static Condition build() {
        return new Condition();
    }

    public static Condition build(Object value) {
        return new Condition(value);
    }

    Condition ifThen(boolean value, Runnable action) {
        if (value) {
            action.run();
        }
        return this;
    }

    Condition elseIfThen(boolean value, Runnable action) {
        if (!executed && value) {
            action.run();
        }
        return this;
    }

    void elseThen(Runnable action) {
        if (!executed) {
            action.run();
        }
    }

    public Condition ifEqualsThen(Object value, Runnable action) {
        if (this.value == null) {
            throw new UnsupportedOperationException("Value of Condition is not define!");
        }
        ifThen(Objects.equals(this.value, value), action);
        return this;
    }

    Condition elseIfEqualsThen(Object value, Runnable action) {
        if (this.value == null) {
            throw new UnsupportedOperationException("Value of Condition is not define!");
        }
        if (!executed) {
            ifEqualsThen(Objects.equals(this.value, value), action);
        }
        return this;
    }
}