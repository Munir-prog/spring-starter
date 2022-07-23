package com.mprog.spring.validation.impl;

import com.mprog.spring.dto.UserCreateEditDto;
import com.mprog.spring.validation.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.springframework.util.StringUtils.*;

@Component
public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateEditDto> {

    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return hasText(value.getFirstname()) || hasText(value.getLastname());
    }
}
