package com.prokopovich.usermanagementapp.util.security;

import com.prokopovich.usermanagementapp.dao.UserAccountDao;
import com.prokopovich.usermanagementapp.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        UserAccount userAccount = userAccountDao.findByUsername(username);
        if (userAccount != null) {
            CustomUserDetails o = CustomUserDetails.fromUserEntityToCustomUserDetails(userAccount);
            System.out.println("---------------------------------------------------------");
            System.out.println(o);
            System.out.println("---------------------------------------------------------");
            return o;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public UserAccount getCurrentUser() {
        String username = null;

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return null;
        }
        if(authentication.getPrincipal() instanceof UserDetails) {
            UserDetails customUserDetails = (UserDetails) authentication.getPrincipal();
            username = customUserDetails.getUsername();
        } else {
            if(authentication.getPrincipal() instanceof String) {
                username = (String) authentication.getPrincipal();
            }
        }
        return userAccountDao.findByUsername(username);
    }
}
