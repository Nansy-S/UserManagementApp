package com.prokopovich.usermanagementapp.dao;

import com.prokopovich.usermanagementapp.entity.UserAccount;
import com.prokopovich.usermanagementapp.exception.DaoException;

import java.util.Collection;

public interface UserAccountDao {

    Collection<UserAccount> findAllByUsername(String username) throws DaoException;

}
