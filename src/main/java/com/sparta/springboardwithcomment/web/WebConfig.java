package com.sparta.springboardwithcomment.web;

import com.sparta.springboardwithcomment.web.inteceptor.LoginCheckInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.Column;

@Component
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/members/add","/css/**","/error", "/boards","/boards/{index}");
    }
}
