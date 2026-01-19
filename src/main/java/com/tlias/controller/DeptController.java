package com.tlias.controller;

import com.tlias.pojo.Dept;
import com.tlias.pojo.Result;
import com.tlias.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    //查询部门方法
    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    public Result list() {

        List<Dept> deptList= deptService.list();
        return Result.success(deptList);
    }

    //删除部门方法
    @RequestMapping(value = "/depts/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable Integer id) {
        deptService.delete(id);
        return Result.success("删除部门成功");
    }

    //新增部门方法
    @RequestMapping(value = "/depts",method = RequestMethod.POST)
    public Result add(@RequestBody Dept dept) {
        deptService.add(dept);
        return Result.success("新增部门成功");
    }


    //根据id查询部门
    @RequestMapping(value = "/depts/{id}",method = RequestMethod.GET)
    public Result get(@PathVariable Integer id) {
        Dept dept=deptService.listById(id);
        System.out.println("controller 层查询到的部门为:"+dept);
        return Result.success(dept);
    }

    //根据id修改部门名称
    @RequestMapping(value = "/depts",method = RequestMethod.PUT)
    //易错点：由于请求的两个参数是封装在请求体中的，@RequestBody 注解的作用是将整个请求体的 JSON 数据解析为一个 Java 对象
    //所以这里千万不能用integer id，string name来当做形参
    public Result update(@RequestBody Dept dept) {
        deptService.update(dept);

        return Result.success("部门名称修改成功");
    }
}
