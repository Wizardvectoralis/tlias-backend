package com.tlias.controller;


import com.tlias.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {


    @PostMapping("/upload")
    /*
    * 方法接收到三个参数后，会将其临时存放在电脑的某个目录，
    * 方法执行完毕，且响应返回给前端后，临时文件会被删除*/
    public Result upload(String username,Integer age, MultipartFile image) throws IOException {
       log.info("传入的参数有：{},{},{}",username,age,image);

        //假设用户电脑中存放着 海边.img，表单项中用image为变量名接收，
        //getName获取的是表单项的名称，也就是image，getOriginalFilenam是获取用户原始文件名的（包括后缀），也就是海边.img
        //String name = image.getName();
        String originalFilename = image.getOriginalFilename();
        //把接收到的文件存储在某个目录中，接收一个File对象
        //新建文件夹后面必须有\\，不然就放咱同级目录下了
        //直接将用户的文件名存放存在问题：假设两个用户的文件名相同，那么后来的用户图片就会覆盖前面的用户数据
        //image.transferTo(new File("C:\\Users\\WangLei\\Pictures\\Screenshots\\新建文件夹\\"+originalFilename));

        //注意：此处需要保证originFileName不为null，否则报错
        //lastindexof方法：返回字符串参数所在的索引，例如“helloworld.vue”.lastindexof(".")，就会返回
        //String extname = originalFilename.substring(originalFilename.lastIndexOf("."));
        //randomUuid:生成一个随机的8-4-4-4-12 分隔的 32 位十六进制字符串（比如 3f2504e0-4f89-41d3-9a0c0305e82c3301）标识符，是一个uuid对象
        //可以调用tostring方法将此uuid对象字符串
        //String newFileName = UUID.randomUUID().toString() + extname;

        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extname;
        image.transferTo(new File("C:\\Users\\WangLei\\Pictures\\Screenshots\\新建文件夹\\"+fileName));

        return Result.success();

        /*一般项目很少直接将用户文件存储到后端服务器中，原因有3：
        1，前端无法访问存储的文件
        2，占用后端空间
        3，磁盘损坏无法恢复用户数据

        现代项目常用两种方式解决文件存储服务：
        1，自己搭建一套文件存储服务例如fastdfs和minio（繁琐）
        2，用别人的服务例如阿里云（付费）

        * */
    }

}
