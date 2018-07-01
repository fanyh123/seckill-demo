package com.yangyang.seckill.config;

import com.yangyang.seckill.daomain.User;
import com.yangyang.seckill.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserServiceImpl userService;
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == User.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        String paramToken = httpServletRequest.getParameter(UserServiceImpl.COOK_NAME_TOKEN);
        String cookieToken = getCookieValue(httpServletRequest,UserServiceImpl.COOK_NAME_TOKEN);
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        // 获取到token后根据token去缓存查找user对象信息
        return  userService.getByToken(token, httpServletResponse);

    }

    /**
     * 拿到httpServletRequest请求中所有cookie，并遍历出需要的cookie
     * @param httpServletRequest
     * @param cookNameToken
     * @return
     */
    private String getCookieValue(HttpServletRequest httpServletRequest, String cookNameToken) {
      Cookie[] cookies =   httpServletRequest.getCookies();
      if(cookies == null || cookies.length<=0){
          return null;
      }
        for (Cookie cookie :cookies) {
            if(cookie.getName().equals(cookNameToken)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
