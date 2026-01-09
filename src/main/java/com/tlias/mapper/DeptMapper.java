package com.tlias.mapper;

import com.tlias.pojo.Dept;
import com.tlias.pojo.Result;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    List<Dept> list();

    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);


    /*mybatis注释的作用：当接口方法的形参是单个自定义对象（如 Dept dept） 时，
    MyBatis 会自动将注释里面 #{变量名} 中的变量名与该对象的 getter 方法名匹配（底层是反射）
     */
    @Insert("insert into dept(name,create_time,update_time)values (#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    /*当mybatis注释有返回值时，例如以下select语句，会返回一个字段或者返回一条记录
    但是mapper接口都是抽象函数没有函数体，所以注释会把返回的结果直接封存在函数返回值类型的一个对象中
    例如本例中会把查询到的语句直接封存在一个dept实例中，以后谁使用这个函数，就可以直接接收到这个实例
     */
    @Select("select * from dept where id=#{id}")
    Dept listById(Integer id);

    @Update("update dept set name=#{name},update_time=now() where id=#{id}")
    void update(Integer id, String name);
}
