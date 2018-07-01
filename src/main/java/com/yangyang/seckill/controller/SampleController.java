package com.yangyang.seckill.controller;

import com.yangyang.seckill.daomain.User;
import com.yangyang.seckill.redis.RedisService;
import com.yangyang.seckill.redis.UserKey;
import com.yangyang.seckill.result.CodeMsg;
import com.yangyang.seckill.result.Result;
import com.yangyang.seckill.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    RedisService redisService;

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","相宜妈妈");
        return "hello";
    }

    @RequestMapping("/resultSuccess")
    @ResponseBody
    public Result<String> resultSuccess(Model model){
        return Result.success("hello,result");
    }

    @RequestMapping("/resultError")
    @ResponseBody
    public Result<String> resultError(Model model){
        return Result.error(CodeMsg.SERVER_ERROR);
    }
    @RequestMapping("/getDb")
    @ResponseBody
    public Result<User> getDb(Model model){
      User user = userService.getById(1);
     return Result.success(user);
      //return Result.success(null);
    }
    @RequestMapping("/redis")
    @ResponseBody
    public Result<User> getRedis(){
        User user  =  new User();
        user.setId(1);
        user.setNickName("yangzai");
        redisService.set(UserKey.getById,""+1,user);
         User value =redisService.get(UserKey.getById,""+1,User.class);

        return Result.success(value);
    }
}

