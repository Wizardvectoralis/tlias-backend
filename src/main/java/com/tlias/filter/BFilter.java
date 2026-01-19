package com.tlias.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

//@WebFilter("/*")//除了用户主动发送的请求，浏览器会默认发送一个/.well-known/appspecific/com.chrome.devtools.json探测请求，因此/*类过滤器可能会触发多次
public class BFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("B Filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        System.out.println("B filter has been called before doFilter");
        System.out.println(httpServletRequest.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("B filter has been called after  doFilter");
    }

    @Override
    public void destroy() {
        System.out.println("B Filter destroyed");
        Filter.super.destroy();

    }
}
