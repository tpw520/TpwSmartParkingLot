package com.tang.tpwsmartparkinglot.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginAdmin = session.getAttribute("loginAdmin");
        if (loginAdmin != null){
            return true;
        }
        request.setAttribute("mas", "请先登录");
        request.getRequestDispatcher("/").forward(request, response);
        return false;
    }

}
