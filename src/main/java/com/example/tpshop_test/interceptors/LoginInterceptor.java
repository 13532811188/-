package com.example.tpshop_test.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.example.tpshop_test.entity.JwtUtil;
import com.example.tpshop_test.entity.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 允许跨域预检请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // 从请求头获取Token
        String token = request.getHeader("Authorization");

        // Token不存在
        if (token == null || token.trim().isEmpty()) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(Result.error("401", "请先登录")));
            return false;
        }

        // 验证Token
        Integer userId = JwtUtil.verifyToken(token);
        if (userId == null) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(Result.error("401", "Token无效或已过期")));
            return false;
        }

        // 将用户ID存入请求属性，供后续接口使用
        request.setAttribute("userId", userId.longValue());
        System.out.println("pass the interceptor");
        return true;
    }
}
