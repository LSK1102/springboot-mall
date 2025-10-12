package com.lsk.springbootmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允許所有 API 路徑
                .allowedOrigins("http://localhost:5173") // 允許的前端來源（你的 Vue 開發伺服器）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允許的 HTTP 方法
                .allowedHeaders("*") // 允許所有請求標頭
                .allowCredentials(true); // 是否允許攜帶 Cookie
    }
}