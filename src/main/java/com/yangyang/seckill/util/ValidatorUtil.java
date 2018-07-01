package com.yangyang.seckill.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具类
 */
public class ValidatorUtil {

    /**
     * 手机号码格式校验，格式：以1开头后面十一位数字
     */
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    /**
     * 校验手机号码格式
     * @param mobile 手机号码
     * @return
     */
    public static boolean isMobile(String mobile){

        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(mobile);
        return  matcher.matches();
    }
}
