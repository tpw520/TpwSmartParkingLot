package com.tang.tpwsmartparkinglot.config;

import com.tang.tpwsmartparkinglot.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
    @Override
//    添加登录拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截对象
        registry.addInterceptor(new LoginInterceptor())
                //拦截所有请求
                .addPathPatterns("/**").
                //放行登录请求和静态资源请求
                        excludePathPatterns("/css/**","/images/**","/login","/","/api/**","/js/**","/lib/**");
    }

}

