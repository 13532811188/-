package com.example.tpshop_test.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HttpConverterConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //其中image表示访问的前缀。"file:F:/img/"是文件真实的存储路径
//        registry.addResourceHandler("/image/**").addResourceLocations("file:F:/img/");
//        registry.addResourceHandler("/images/**").addResourceLocations("file:src/main/resources/static/images");
        registry.addResourceHandler("/target/**").addResourceLocations("file:src/main/resources/static/target");
        registry.addResourceHandler("/images1/**").addResourceLocations("file:src/main/resources/static/images1");
    }

}

