package com.yangyang.seckill.service;

import com.yangyang.seckill.daomain.User;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

     User getById(long id);


    User getByToken(String token, HttpServletResponse httpServletResponse);
}
