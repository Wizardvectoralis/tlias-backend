package com.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Long id;
    private String username;
    private String password;
    private String name;
    private Integer gender;//1男，2女
    private String image;
    private Integer job; //说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管, 5 咨询师'
    private LocalDate entrydate;
    private Integer deptId;//1-5之间的数
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
