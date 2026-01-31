package com.tlias.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.tlias.Utils.JwtUtils;
import com.tlias.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component//用于在配置类中依赖注入
public class LoginInterceptor implements HandlerInterceptor {

    //请求业务执行前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandle...");
        //已经是httpservletrequest了无需转换
        //获取请求url
        String url=request.getRequestURL().toString();
        if (url.contains("login")) {
            return true;
        }

        //尝试获取token（令牌）,如果令牌为空或null，返回false
        String jwt=request.getHeader("token");
        if (!StringUtils.hasLength(jwt)){

            //创建相应结果对象
            Result responseResult=Result.error("no jwt token");
            //把响应结果转化为JSON格式字符串
            String json= JSONObject.toJSONString(responseResult);
            //告知前端响应数据类型和编码
            response.setContentType("application/json;charset=utf-8");
            //获取字符流输出流到响应体，并将json字符串写入到响应体中
            response.getWriter().write(json);
            return false;
        }

        try{
            JwtUtils.parseJwt(jwt);
        }catch (Exception e){
            //创建响应结果对象
            Result responseResult=Result.error(e.getMessage());
            //将响应结果对象转化为JSON字符串
            String json=JSONObject.toJSONString(responseResult);
            //设置响应头，告知前端响应数据类型和编码
            response.setContentType("application/json;charset=utf-8");
            //获取字符串输出流到响应体，并将响应数据写到响应体中
            response.getWriter().write(json);
            return false;
        }


        //经过前面校验，jwt令牌非空、有效，则放行
        return true;
    }

    //请求代码执行完毕后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("posthandle...");
    }


    //做一些收尾工作，只要preHandler返回值为true就执行，否则不执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterCompletion...");
    }
}
