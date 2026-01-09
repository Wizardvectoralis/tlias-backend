package com.tlias.service.impl;
import com.tlias.mapper.DeptMapper;
import com.tlias.pojo.Dept;
import com.tlias.pojo.Result;
import com.tlias.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    //注入deptMapper接口（这个接口的注释是mapper，所以不用创建这个接口的实现类对象
    //但是在DeptController类中，注入的是service接口，这个接口本身没有注释，只是在它的实现类对象上加service注释
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list() {

        List<Dept> deptList= deptMapper.list();
        return deptList;
    }

    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @Override
    public Dept listById(Integer id) {
        Dept dept= deptMapper.listById(id);

        return dept;

    }

    //根据id值修改部门名称
    @Override
    public void update(Dept dept) {
        deptMapper.update(dept.getId(),dept.getName());
    }
}
