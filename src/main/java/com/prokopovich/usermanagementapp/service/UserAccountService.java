package com.prokopovich.usermanagementapp.service;

import com.prokopovich.usermanagementapp.entity.UserAccount;

import java.util.List;

public interface UserAccountService {

    UserAccount addNewUser(UserAccount user);

    boolean updateUser(UserAccount user);

    UserAccount getByUserId(int id);

    List<UserAccount> getAllUser();

    List<UserAccount> changeUserStatus(String status);
}
