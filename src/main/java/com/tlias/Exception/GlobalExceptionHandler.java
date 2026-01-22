package com.tlias.Exception;

import com.tlias.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //代表定义了一个全局异常处理器
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) //指定当前方法捕获哪一类型的异常
    public Result exception(Exception e) {

        e.printStackTrace();
        return Result.error(e.getMessage());
    }
}
