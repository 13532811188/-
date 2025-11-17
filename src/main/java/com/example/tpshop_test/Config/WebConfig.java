package com.example.tpshop_test.Config;


import com.example.tpshop_test.interceptors.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有购物车接口，放行登录和注册接口
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/cart/**") // 拦截购物车相关接口
                .excludePathPatterns("/login", "/register"); // 放行登录注册
    }
}
