package com.prokopovich.usermanagementapp.service;

import com.prokopovich.usermanagementapp.dao.UserAccountDao;
import com.prokopovich.usermanagementapp.entity.UserAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountDao userDao;

    public UserAccountServiceImpl(UserAccountDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserAccount addNewUser(UserAccount user) {
        return null;
    }

    @Override
    public boolean updateUser(UserAccount user) {
        return false;
    }

    @Override
    public UserAccount getByUserId(int id) {
        return null;
    }

    @Override
    public List<UserAccount> getAllUser() {
        return (List<UserAccount>) userDao.findAll();
    }

    @Override
    public List<UserAccount> changeUserStatus(String status) {
        return null;
    }
}
