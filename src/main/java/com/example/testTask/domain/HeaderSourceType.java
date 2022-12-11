package com.example.testTask.domain;

import java.util.Arrays;

public enum HeaderSourceType {

    MAIL("mail"),
    MOBILE("mobile"),
    BANK("bank"),
    GOSUSLUGI("gosuslugi"),
    UNKNOWN("UNKNOWN");

    private final String sourceName;

    HeaderSourceType(String name) {
        this.sourceName = name;
    }

    public static HeaderSourceType getBySourceName(String sourceName) {
        return Arrays.stream(HeaderSourceType.values())
                .filter(headerSourceType -> headerSourceType.sourceName.equals(sourceName))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
