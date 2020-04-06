package com.xschen.community.majinag.interceptor;

import com.xschen.community.majinag.mapper.UserMapper;
import com.xschen.community.majinag.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用Service让Spring接管
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;

    /**
     * 将获取user的逻辑放到拦截器内，并设置为请求所有的地址都被拦截，这样就不用了在所有页面都判断一次了
     * 返回true表示可以执行下去
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
//        防止空指针异常
        // 获取cookie后，到数据库中查询是否存在这个token来获取用户信息
        if (cookies != null && cookies.length !=0){
            for (Cookie cookie: cookies) {
                if (cookie.getName() == "token") {
                    String value = cookie.getValue();
                    User user = userMapper.findByToken(value);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
