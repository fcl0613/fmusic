package com.fcl.fmusic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 访问静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/singerPic/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") +
                        System.getProperty("file.separator") +
                        "img" + System.getProperty("file.separator") +
                        "singer_img" + System.getProperty("file.separator"));
    }

    /**
     * 解决跨域请求
     * 在客户端 前后端分离需要用到
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("**")
                .allowedMethods("*")
                .allowCredentials(true);
    }
}
