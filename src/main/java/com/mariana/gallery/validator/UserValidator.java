package com.mariana.gallery.validator;

import com.mariana.gallery.persistence.user.User;
import com.mariana.gallery.persistence.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.NoResultException;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.rejectValue("login", "Size.userForm.username");

        }
        try {
            userDAO.findUserByUsername(user.getUsername());
            if (userDAO.findUserByUsername(user.getUsername()) != null) {
                errors.rejectValue("login", "Duplicate.userForm.username");
            }

        } catch (NoResultException e) {
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 3 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
