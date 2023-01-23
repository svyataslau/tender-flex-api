package com.tenderflex.tenderflex.config;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    TENDER_READ("tender:read"),
    TENDER_WRITE("tender:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
