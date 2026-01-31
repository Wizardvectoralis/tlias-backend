package com.tlias.Exception;

import com.tlias.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //代表定义了一个全局异常处理器，能够捕获所有加了restcontroller注解的类的异常
// RestControllerAdvice=ControllerAdvice+ResponseBody，所以会将对象转换为JSON字符串发送给前端
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) //指定当前方法捕获哪一类型的异常，Exception.class代表捕获所有异常
    public Result exception(Exception e) {

        e.printStackTrace();//输出异常的堆栈信息
        return Result.error("操作失败："+e.getMessage());
    }
}
