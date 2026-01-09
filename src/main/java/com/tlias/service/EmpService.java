package com.tlias.service;

import com.tlias.pojo.PageBean;

import java.time.LocalDate;

public interface EmpService {
    PageBean getEmps(String name,
                     int gender,
                     LocalDate begin,
                     LocalDate end,
                     long page,
                     long pageSize);
}
