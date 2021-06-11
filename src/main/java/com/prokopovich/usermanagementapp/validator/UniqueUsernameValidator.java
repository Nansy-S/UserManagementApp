package com.prokopovich.usermanagementapp.validator;

import com.prokopovich.usermanagementapp.dao.UserAccountDao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserAccountDao userDao;

    public UniqueUsernameValidator(UserAccountDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void initialize(UniqueUsername paramA) { }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext ctx) {
        if(username == null) {
            return false;
        }
        if(userDao.findAllByUsername(username).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
