package com.incretio.cozy_time_tracker_server.helpers;

import java.util.Objects;

public class TextSplitOnNumberAndName {
    private final String text;

    public TextSplitOnNumberAndName(String text) {
        Objects.requireNonNull(text);
        this.text = text.trim();
    }

    public String getNumber() {
        String result = text;
        if (text.contains(" ")) {
            result = text.substring(0, text.indexOf(" "))
                         .trim();
        }
        return result;
    }

    public String getName() {
        String result = "";
        if (text.contains(" ")) {
            result = text.substring(text.indexOf(" "))
                         .trim();
        }
        return result;
    }
}
