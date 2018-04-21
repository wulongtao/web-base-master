package com.xxh.web.controller.validator;

import com.xxh.web.controller.bean.User;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author 小小黑
 */
@Repository("userValidator")
public class UserValidator implements Validator {
    private static final int MAX_LOGINNAME_LENGTH = 10;
    private static final int MIN_LOGINNAME_LENGTH = 6;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "loginname", null, "登录名不能为空");
//        ValidationUtils.rejectIfEmpty(errors, "password", null, "密码不能为空");

        User user = (User) o;
        if (user.getLoginname().length() > MAX_LOGINNAME_LENGTH) {
            errors.rejectValue("loginname", null, "用户名不能超过10个字符");
        }
        if (user.getLoginname().length() < MIN_LOGINNAME_LENGTH) {
            errors.rejectValue("loginname", null, "用户名不能少于6个字符");
        }
    }
}
