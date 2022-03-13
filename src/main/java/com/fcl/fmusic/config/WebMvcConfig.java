package com.fcl.fmusic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 访问静态资源 图片 歌曲等
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //首页轮播图资源路径
        registry.addResourceHandler("/img/banner/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") +
                        System.getProperty("file.separator") + "img" +
                        System.getProperty("file.separator") + "banner");

        //歌曲图资源路径
        registry.addResourceHandler("/img/singerPic/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") +
                        System.getProperty("file.separator") +
                        "img" + System.getProperty("file.separator") +
                        "singer_img" + System.getProperty("file.separator"));

        //歌曲图片路径
        registry.addResourceHandler("/img/songPic/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") +
                        System.getProperty("file.separator") +
                        "img" + System.getProperty("file.separator") +
                        "song_img" + System.getProperty("file.separator"));

        //歌单图片路径
        registry.addResourceHandler("/img/songListPic/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") +
                        System.getProperty("file.separator") +
                        "img" + System.getProperty("file.separator") +
                        "songlist_img" + System.getProperty("file.separator"));

        //用户头像路径
        registry.addResourceHandler("/img/userPic/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") +
                        System.getProperty("file.separator") +
                        "img" + System.getProperty("file.separator") +
                        "user_img" + System.getProperty("file.separator"));

        //音乐路径
        registry.addResourceHandler("/music/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") +
                        System.getProperty("file.separator") + "music" +
                        System.getProperty("file.separator"));
    }

    /**
     * 解决跨域请求
     * 在客户端 前后端分离需要用到
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
