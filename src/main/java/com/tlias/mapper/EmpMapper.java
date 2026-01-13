package com.tlias.mapper;

import com.tlias.pojo.Emp;
import com.tlias.pojo.Result;
import org.apache.ibatis.annotations.Insert;
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

    //delete方法会默认返回一个int值
    int deleteEmpsByIds(List<Long> ids);

    @Insert("insert into emp" +
            "(username, name, gender, entrydate,  create_time, update_time) " +
            "VALUES" +
            "(#{username},#{name},#{gender},#{entrydate},#{createTime},#{updateTime}) ")
    void addEmp(Emp emp);

    @Select("select * from emp where id=#{id}")
    Emp getEmpById(Long id);
}
