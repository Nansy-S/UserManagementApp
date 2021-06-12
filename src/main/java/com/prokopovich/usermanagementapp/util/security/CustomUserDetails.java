package com.prokopovich.usermanagementapp.util.security;

import com.prokopovich.usermanagementapp.entity.UserAccount;
import com.prokopovich.usermanagementapp.enumeration.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private String status;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(UserAccount userAccount) {
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.username = userAccount.getUsername();
        userDetails.password = userAccount.getPassword();
        userDetails.status = userAccount.getStatus();
        userDetails.grantedAuthorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + userAccount.getRole()));
        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(this.status == null) return true;
        if(!this.status.equals(UserStatus.INACTIVE.getTitle())) return true;
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}