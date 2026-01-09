package com.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

/*
* 此类型用来封装分页查询的结果，分页查询后端需要返回给前端两个结果：
* 一个是总数据条数，一个是每一页的结果*/
public class PageBean {
    private long page;
    private List<Emp> emps;

}
