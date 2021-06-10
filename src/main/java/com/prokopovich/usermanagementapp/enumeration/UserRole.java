package com.prokopovich.usermanagementapp.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum UserRole {

    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private final String title;

    UserRole(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static UserRole fromString(String text) {
        for (UserRole b : UserRole.values()) {
            if (b.title.equals(text)) {
                return b;
            }
        }
        return null;
    }

    public static List<String> getAllTitle() {
        List<String> roleTitleList = new ArrayList<>();
        for(UserRole value : UserRole.values()) {
            roleTitleList.add(value.getTitle());
        }
        return roleTitleList;
    }
}
