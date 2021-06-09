package com.prokopovich.usermanagementapp.dao;

import com.prokopovich.usermanagementapp.entity.UserAccount;
import com.prokopovich.usermanagementapp.exception.DaoException;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserAccountDaoImpl implements UserAccountDao {

    @Override
    public Collection<UserAccount> findAllByUsername(String username) throws DaoException {
        return null;
    }
}
