package com.lnet.tms.service.sys;

import com.lnet.tms.model.sys.SysList;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SysListValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SysList.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
