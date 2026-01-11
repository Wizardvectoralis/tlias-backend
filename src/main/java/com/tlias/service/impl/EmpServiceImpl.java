package com.tlias.service.impl;

import com.tlias.mapper.EmpMapper;
import com.tlias.pojo.Emp;
import com.tlias.pojo.PageBean;
import com.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
//service层需要计算出：1，总共有多少条数据；2，某一页的具体数据
    @Autowired
    private EmpMapper empMapper;



    @Override
    public PageBean getEmps(String name, int gender, LocalDate begin,LocalDate end,
                            long page, long pageSize) {

        long startPage=(page-1)*pageSize;
        long empNumber= empMapper.getEmpNumber(name,gender,begin,end);
        List<Emp> empsInOnePage=empMapper.getEmpsInOnePage(startPage,name,gender,begin,end,pageSize);

        return new PageBean(empNumber,empsInOnePage);
    }

    @Override
    public int deleteEmpsByIds(List<Long> ids) {

        int deletedNum= empMapper.deleteEmpsByIds(ids);

        return deletedNum;
    }

    @Override
    public void addEmp(Emp emp) {


        emp.setEntrydate(LocalDate.now());
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);
    }
}
