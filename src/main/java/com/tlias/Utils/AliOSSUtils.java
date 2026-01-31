package com.tlias.Utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.tlias.config.AliOSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.UUID;

@Component
public class AliOSSUtils {
    @Autowired
    AliOSSProperties aliOSSProperties;

    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//    @Value("${aliyun.oss.endpoint}")
//    private String endpoint;//为了演示注解注入值，暂时不用final修饰
//
//    // 填写Bucket名称，例如examplebucket。
//    private final String bucketName = "tlias20260112";


    //String filePath= "C:\\Users\\WangLei\\Pictures\\Screenshots\\新建文件夹屏幕截图 2025-08-11 214321.png";
    // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
    private final String region = "cn-beijing";

    public String upload(MultipartFile multipartFile) throws Exception {

        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(aliOSSProperties.getEndpoint())
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。


        String originalFilename = multipartFile.getOriginalFilename();

        String objectName="exampledir/"
                + UUID.randomUUID().toString()
                +originalFilename.substring(originalFilename.lastIndexOf("."));
        InputStream multipartFileInputStream= multipartFile.getInputStream();
        PutObjectRequest putObjectRequest = new PutObjectRequest(aliOSSProperties.getBucketName(), objectName, multipartFileInputStream);
        // 创建PutObject请求。
        PutObjectResult result = ossClient.putObject(putObjectRequest);
        if (ossClient != null) {
            ossClient.shutdown();
        }




        return "https://tlias20260112.oss-cn-beijing.aliyuncs.com/"+ objectName;
    }

}
