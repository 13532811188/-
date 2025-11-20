package com.example.tpshop_test.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有接口允许跨域
                .allowedOrigins("http://localhost:5500") // 允许前端 HTTP 地址
                .allowedMethods("GET", "POST", "OPTIONS") // 允许请求方法（含预检 OPTIONS）
                .allowedHeaders("*") // 允许所有请求头（如 Content-Type）
                .allowCredentials(true) // 允许携带 Token/Cookie
                .maxAge(3600); // 预检请求缓存 1 小时
    }
}