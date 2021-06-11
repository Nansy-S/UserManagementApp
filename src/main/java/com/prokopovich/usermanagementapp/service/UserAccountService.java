package com.prokopovich.usermanagementapp.service;

import com.prokopovich.usermanagementapp.entity.UserAccount;

import java.util.List;

public interface UserAccountService {

    UserAccount addNewUser(UserAccount newUser);

    boolean updateUser(UserAccount user);

    UserAccount getByUserId(int id);

    List<UserAccount> getAllUser();

    void changeUserStatus(int id, String status);

    List<UserAccount> findByUsername(String username);

    List<UserAccount> filterUser(String username, String role, String status);
}
