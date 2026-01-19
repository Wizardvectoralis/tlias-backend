package com.tlias.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
/*拦截具体路径:/login	只有访问 /login 路径时，才会被拦截,其他诸如/login/2也不会被拦截
目录拦截:	/emps/*	    访问/emps下的所有资源，都会被拦截
拦截所有:	/*	        访问所有资源，都会被拦截

@WebFilter(urlPatterns = "/depts")中的urlPatterns可省略*/


//@WebFilter("/depts/*")//如果只写"/depts"，那么访问路径参数例如"/depts/1"的时候该过滤器就不会被执行，因为"/depts"是精确匹配。
public class AFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("A Filter initiated");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("A filter has been called before doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("A filter has been called after  doFilter");
    }

    @Override
    public void destroy() {
        System.out.println("A Filter destroyed");
        Filter.super.destroy();

    }
}
