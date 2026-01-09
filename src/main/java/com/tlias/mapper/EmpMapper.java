package com.tlias.mapper;

import com.tlias.pojo.Emp;
import com.tlias.pojo.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {



    long getEmpNumber(String name,
                      int gender,
                      LocalDate begin,
                      LocalDate end);


    List<Emp> getEmpsInOnePage(long startPage,
                               String name,
                               int gender,
                               LocalDate begin,
                               LocalDate end,

                               long pageSize);
}
