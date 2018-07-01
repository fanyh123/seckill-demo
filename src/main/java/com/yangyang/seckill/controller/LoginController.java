package com.yangyang.seckill.controller;

import com.yangyang.seckill.result.Result;
import com.yangyang.seckill.service.Impl.UserServiceImpl;
import com.yangyang.seckill.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/toLogin")
    public String toLogin() {

        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse httpServletResponse,@Valid LoginVo loginVo) {
        // 登录
        userService.login(httpServletResponse,loginVo);
        return Result.success(true);
    }
}
