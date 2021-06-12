package com.prokopovich.usermanagementapp.dao;

import com.prokopovich.usermanagementapp.entity.UserAccount;
import com.prokopovich.usermanagementapp.exception.DaoException;

import java.util.Collection;

public interface UserAccountDao {

    UserAccount create(UserAccount newUser) throws DaoException;

    boolean update(UserAccount user) throws DaoException;

    UserAccount findOne(int id) throws DaoException;

    Collection<UserAccount> findAll() throws DaoException;

    UserAccount findByUsername(String username) throws DaoException;

    Collection<UserAccount> findByUsernameAndRoleAndStatus(String username, String role,
                                                           String status) throws DaoException;
}
