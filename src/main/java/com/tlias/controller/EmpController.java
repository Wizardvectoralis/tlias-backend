package com.tlias.controller;

import com.tlias.pojo.Emp;
import com.tlias.pojo.PageBean;
import com.tlias.pojo.Result;
import com.tlias.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    //根据条件查询员工如无条件则全部显示
    public Result getEmps(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int gender,
            @RequestParam(required = false) LocalDate begin,
            @RequestParam(required = false) LocalDate end,
            @RequestParam(defaultValue = "1") long page,
            @RequestParam(defaultValue = "10") long pageSize ) {
        log.info("controller getEmps get parms name:{},gender:{},begin:{},end:{},page:{},pageSize:{}",
                name,gender,begin,end,page,pageSize);

        PageBean pageBean= empService.getEmps(name,gender,begin,end,page,pageSize);
        return Result.success(pageBean);
    }

    //根据id批量删除用户，同时返回删除的条目数
    @DeleteMapping("/emps/{ids}")
    /*pathvariable注解的作用原本是将占位符对应的位置转化为单个对象，
    可以考虑将传过来的东西转化为单个字符串-字符串数组-再遍历为Long集合
    不知道为什么黑马要在路径参数传递数字集合，建议通过Params参数传递
    */
    public Result deleteEmpsByIds(@PathVariable List<Long> ids) {


        try {
            int deletedNum= empService.deleteEmpsByIds(ids);
            return Result.success(deletedNum+"条项目删除成功");
        }catch (Exception e) {

            return Result.error("controller层的方法出现问题："+e.getMessage());
        }
    }

    //添加员工
    @PostMapping("/emps")
    public Result addEmp(@RequestBody Emp emp) {

        empService.addEmp(emp);
        return Result.success("添加成功");
    }

    //根据id查询员工
    @GetMapping("/emps/{id}")
    public Result getEmpById(@PathVariable Long id){

        Emp emp= empService.getEmpById(id);

        return Result.success(emp);
    }


}
