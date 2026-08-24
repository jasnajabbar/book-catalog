package com.bookcatalog.dto;

public enum BookEnumParam {
    IN_PROGRESS("IN PROGRESS"),
    COMPLETED("COMPLETED"),
    UNREAD("NOT STARTED");

    private String displayName;

    BookEnumParam(String displayName) {
        this.displayName = displayName;
    }
}
