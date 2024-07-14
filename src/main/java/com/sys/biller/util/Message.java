package com.sys.biller.util;

import lombok.Data;

public enum Message {
    SUCCESS(1,"success"),
    FAIL(2,"fail");

    private final int code;
    private final String description;

    Message(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
