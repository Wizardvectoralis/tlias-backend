package com.tlias.service;

import com.tlias.pojo.Dept;
import com.tlias.pojo.Result;

import java.util.List;

public interface DeptService {
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    Dept listById(Integer id);

    void update(Dept dept);
}
