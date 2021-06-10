package com.prokopovich.usermanagementapp.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum UserStatus {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String title;

    UserStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static UserStatus fromString(String text) {
        for (UserStatus b : UserStatus.values()) {
            if (b.title.equals(text)) {
                return b;
            }
        }
        return null;
    }

    public static List<String> getAllTitle() {
        List<String> statusTitleList = new ArrayList<>();
        for(UserStatus value : UserStatus.values()) {
            statusTitleList.add(value.getTitle());
        }
        return statusTitleList;
    }
}
