package com.tlias.controller;

import com.tlias.pojo.PageBean;
import com.tlias.pojo.Result;
import com.tlias.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
public class  EmpController {
    /*
     分页查询员工列表
     前端传入的数据：分页查询的页码page&每页的记录数pageSize
     1，需要后端返回查询到的员工列表
     2，需要后端返回总共查询到多少条数据
     3，不需要后端返回总页数，可以前端用总记录数/每页显示数，向上取整
     */
    //分页查询的函数，接受前端页码和每页记录数两个函数，返回一个pagebean

    @Autowired
    private EmpService empService;

    @GetMapping("/emps")
    //RequestParams中的defaultValue属性：当前端未传page和pageZize时，page默认为1，pageSize默认默认为10
    //根据条件查询员工
    public Result getEmps(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int gender,
            @RequestParam(required = false) LocalDate begin,
            @RequestParam(required = false) LocalDate end,
            @RequestParam(defaultValue = "1") long page,
            @RequestParam(defaultValue = "10") long pageSize ) {


        log.info("哈哈哈+++++controller getEmps get parms name:{},gender:{},begin:{},end:{},page:{},pageSize:{}",
                name,gender,begin,end,page,pageSize);

        PageBean pageBean= empService.getEmps(name,gender,begin,end,page,pageSize);
        return Result.success(pageBean);
    }


}
