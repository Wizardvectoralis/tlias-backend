package com.tlias.Utils;

import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

public class AliOSSUtils {


    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。

    // 填写Bucket名称，例如examplebucket。
    String bucketName = "tlias20260112";
    // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
    String objectName = "exampledir/oss1.png";
    // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
    // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
    String filePath= "C:\\Users\\WangLei\\Pictures\\Screenshots\\新建文件夹屏幕截图 2025-08-11 214321.png";
    // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
    String region = "cn-beijing";


    public String upload(MultipartFile multipartFile) throws Exception {

        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        InputStream multipartFileInputStream= multipartFile.getInputStream();






        return null;
    }

}
