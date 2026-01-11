package com.tlias.service;

import com.tlias.pojo.Emp;
import com.tlias.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageBean getEmps(String name,
                     int gender,
                     LocalDate begin,
                     LocalDate end,
                     long page,
                     long pageSize);

    int deleteEmpsByIds(List<Long> ids);

    void addEmp(Emp emp);
}
