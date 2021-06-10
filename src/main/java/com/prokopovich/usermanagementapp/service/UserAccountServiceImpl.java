package com.prokopovich.usermanagementapp.service;

import com.prokopovich.usermanagementapp.dao.UserAccountDao;
import com.prokopovich.usermanagementapp.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserAccountServiceImpl(UserAccountDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserAccount addNewUser(UserAccount newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setCreatedAt(LocalDateTime.now());
        newUser = userDao.create(newUser);
        return newUser;
    }

    @Override
    public boolean updateUser(UserAccount user) {
        return userDao.update(user);
    }

    @Override
    public UserAccount getByUserId(int id) {
        return userDao.findOne(id);
    }

    @Override
    public List<UserAccount> getAllUser() {
        return (List<UserAccount>) userDao.findAll();
    }

    @Override
    public String changeUserStatus(int id, String status) {
        String result = "";
        UserAccount user = userDao.findOne(id);
        if(user.getStatus().equals(status)) {
            result = "This user is already " + status + ".";
        } else {
            result = userDao.update(user) ? "User status changed." : "Server error.";
        }
        return result;
    }

    @Override
    public List<UserAccount> findByUsername(String username) {
        return (List<UserAccount>) userDao.findAllByUsername(username);
    }

    @Override
    public List<UserAccount> findByRole(String role) {
        return (List<UserAccount>) userDao.findAllByRole(role);
    }

    @Override
    public List<UserAccount> findByStatus(String status) {
        return (List<UserAccount>) userDao.findAllByStatus(status);
    }
}
