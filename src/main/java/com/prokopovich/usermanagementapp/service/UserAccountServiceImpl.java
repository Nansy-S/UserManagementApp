package com.prokopovich.usermanagementapp.service;

import com.prokopovich.usermanagementapp.dao.UserAccountDao;
import com.prokopovich.usermanagementapp.entity.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private static final Logger LOGGER = LogManager.getLogger(UserAccountServiceImpl.class);

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
        UserAccount prevInfoUser = getByUserId(user.getId());
        user.setPassword(prevInfoUser.getPassword());
        user.setStatus(prevInfoUser.getStatus());
        user.setCreatedAt(prevInfoUser.getCreatedAt());
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
    public void changeUserStatus(int id, String status) {
        String result = "";
        UserAccount user = userDao.findOne(id);
        if(user.getStatus().equals(status)) {
            result = "This user is already " + status + ".";
        } else {
            user.setStatus(status);
            result = userDao.update(user) ? "User status changed." : "Server error.";
        }
        LOGGER.info(result);
    }

    @Override
    public UserAccount findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<UserAccount> filterUser(String username, String role, String status) {
        if(username == null) username = "";
        if(role == null) role = "";
        if(status == null) status = "";
        return (List<UserAccount>) userDao.findByUsernameAndRoleAndStatus(username, role, status);
    }
}
