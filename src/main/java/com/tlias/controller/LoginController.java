package com.tlias.controller;

import com.tlias.Utils.JwtUtils;
import com.tlias.pojo.Emp;
import com.tlias.pojo.Result;
import com.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){

        //如果传递进来的用户名和密码为空则返回错误响应结果
        if (emp.getUsername()==null||emp.getPassword()==null){
            return Result.error("用户名或密码不能为空");
        }
        Emp gotEmp= empService.getEmpByUserNameAndPassword(emp);

        //如果查询到的结果不为空，则将jwt令牌返回给前端
        if (gotEmp!=null){
            Map<String,Object> map = new HashMap<>();
            map.put("id",gotEmp.getId());
            map.put("username",gotEmp.getUsername());
            map.put("name",gotEmp.getName());

            String jwt = JwtUtils.generateToken(map, 3600000L);
            return Result.success(jwt);
        }

        return Result.error("登录失败");
    }

}
