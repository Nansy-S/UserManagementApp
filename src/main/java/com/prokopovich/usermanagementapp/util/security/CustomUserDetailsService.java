package com.prokopovich.usermanagementapp.util.security;

import com.prokopovich.usermanagementapp.dao.UserAccountDao;
import com.prokopovich.usermanagementapp.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountDao userAccountDao;

    @Autowired
    public CustomUserDetailsService(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = (UserAccount) userAccountDao.findAllByUsername(username);
        if (userAccount != null) {
            return CustomUserDetails.fromUserEntityToCustomUserDetails(userAccount);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
