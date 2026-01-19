package com.tlias.filter;

import com.alibaba.fastjson2.JSONObject;
import com.tlias.Utils.JwtUtils;
import com.tlias.pojo.Result;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;
/*过滤器使用方法：
* 1.实现接口Filter
* 2.添加注解@WebFilter+@ServletComponentScan
* 3.链式执行顺序：按过滤器类名顺序执行，A过滤器dofilter前方法-B过滤器dofilter前方法-本身的业务代码-B过滤器dofilter后方法-A过滤器dofilter后方法
* 4.U型结构*/

//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //强制转换类型以便使用其特有方法

        String url=httpServletRequest.getRequestURL().toString();
        //获取请求url

        if(url.contains("/login")){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //判断请求中是否包含login，如果包含则放行，并结束当前方法。
        //如果光放行不结束方法，则执行完业务代码后还会回到doFilter方法中的chain.difilter方法 =>>U型结构

        String token=httpServletRequest.getHeader("token");//从请求头中获取token

        if(!StringUtils.hasLength(token)){  //判断一个字符串是否非null且长度大于0,在org.springframework.util包下
            Result responseResult=Result.error("未登录");
            String json= JSONObject.toJSONString(responseResult);
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(json);
            return;
        }
        //如果token为null或长度小于零则说明没有token，生成一个“未登录”的result对象，将其转化为JSON字符串
        // 设置响应头setContentType("application/json;charset=utf-8")告诉前端数据类型和编码方式（如果是springboot框架例如restcontroller层则会自动设置无需手动）
        //获取字符输出流到响应体，并将JSON字符串写入到响应体中，结束doFilter方法
        try {
            JwtUtils.parseJwt(token);
        }catch (Exception e){
            Result responseResult=Result.error(e.getMessage());
            String json= JSONObject.toJSONString(responseResult);
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(json);
            return;
        }
        //若token长度不为零，尝试解析该令牌
        //如果解析失败，则将失败结果返回给前端

        filterChain.doFilter(httpServletRequest,httpServletResponse);
        //如果解析成功则放行
    }

    @Override
    public void destroy() {
        Filter.super.destroy();

    }
}
