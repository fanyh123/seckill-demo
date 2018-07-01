package com.yangyang.seckill.service.Impl;

import com.yangyang.seckill.dao.UserDao;
import com.yangyang.seckill.daomain.User;
import com.yangyang.seckill.exception.GlobalException;
import com.yangyang.seckill.redis.RedisService;
import com.yangyang.seckill.redis.SeckillUserKey;
import com.yangyang.seckill.result.CodeMsg;
import com.yangyang.seckill.service.UserService;
import com.yangyang.seckill.util.MD5Util;
import com.yangyang.seckill.util.UUIDUtil;
import com.yangyang.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@ComponentScan
@Service
@Transactional
public class UserServiceImpl implements UserService {

    public static final String COOK_NAME_TOKEN = "token";
    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;


    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User getByToken(String token, HttpServletResponse httpServletResponse) {
        if (token == null) {
            return null;
        }
        User user = redisService.get(SeckillUserKey.token,token,User.class);
        if (user != null) {

            // 延长session过期时间
            addCookie(httpServletResponse, token, user);
        }
        return user;
    }

    public boolean login(HttpServletResponse httpServletResponse,LoginVo loginVo) {
        if (loginVo == null) {

            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formpass = loginVo.getPassword();
        User user = getById(Long.valueOf(mobile));
        // 判断手机号是否存在
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        // 验证密码
        String dbPass = user.getPassWord();
        String slatDB = user.getSalt();
        // 根据用户输入的密码和数据库salt计算出密码
        String calcPass = MD5Util.formPassToDBPass(formpass, slatDB);
        // 判断计算结果是否和数据库相同
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        // 生成token
        String token = UUIDUtil.uuid();
        // 生成cookie
        addCookie(httpServletResponse,token, user);
        return true;
    }

    /**
     * 生产cookie并设置缓存
     * @param httpServletResponse
     * @param token
     * @param user
     */
    private void addCookie(HttpServletResponse httpServletResponse, String token, User user) {
        // 把用户信息放入redis
        redisService.set(SeckillUserKey.token,token,user);
        Cookie cookie = new Cookie(COOK_NAME_TOKEN,token);
        cookie.setMaxAge(SeckillUserKey.token.expireSeconds());
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }
}
