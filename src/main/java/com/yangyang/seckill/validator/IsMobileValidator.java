package com.yangyang.seckill.validator;

import com.yangyang.seckill.util.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {
    private boolean required = false;
    @Override
    public void initialize(IsMobile isMobile) {
       required= isMobile.required();
    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isMobile(mobile);
        }else {
            if (StringUtils.isEmpty(mobile)){
                return true;
            }else {
                return ValidatorUtil.isMobile(mobile);
            }
        }
    }
}
