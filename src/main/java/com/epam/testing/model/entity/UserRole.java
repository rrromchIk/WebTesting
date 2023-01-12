package com.epam.testing.model.entity;

/**
 * Role enum
 *
 * @author rom4ik
 */

public enum UserRole {
    ADMIN("admin"),
    CLIENT("client"),
    GUEST("guest");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public static UserRole getRole(String role) {
        return role.equals("admin") ? ADMIN : CLIENT;
    }

    public String getName() {
        return name;
    }
}
