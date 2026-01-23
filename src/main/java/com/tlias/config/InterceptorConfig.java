package com.tlias.config;


import com.tlias.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class  InterceptorConfig implements WebMvcConfigurer {
    //注入自定义的拦截器对象
    @Autowired
    private LoginInterceptor loginInterceptor;

    //重写addInterceptors方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/emps/**").excludePathPatterns("/depts/**");
        //拦截除了Login以外的所有路径
        /*
        * 1. "/**"          拦截所有路径
        * 2. "/depts/*"     拦截depts下的一级路径例如"/depts/3"，但无法拦截"/depts/1/2"
        * 3."/depts/**      拦截depts"下的所有路径*/
    }
}
