package com.soft1851.music.admin.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Yujie_Zhao
 * @ClassName AliOssUtil
 * @Description TODO
 * @Date 2020/5/1  15:38
 * @Version 1.0
 **/
@Slf4j
public class AliOssUtil {
    public static String upload(MultipartFile sourceFile){
        // 获取文件名
        String fileName = sourceFile.getOriginalFilename();
        //uuid生成主文件名
        String prefix = UUID.randomUUID().toString();
        assert fileName != null;
        //源文件的扩展名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //创建File类型的临时文件
        File tempFile = null;
        try {
            tempFile = File.createTempFile(prefix,suffix);
            //将MultipartFile转换成File
            sourceFile.transferTo(tempFile);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        assert tempFile != null;
        return upload(tempFile);
    }
    public static String upload(File file){
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
//        String accessKeyId = "LTAI4FvqK568YDj7HtsPEZ5d";
        String accessKeyId = "LTAI4FiNz28RZdADSiGjabuj";
//        String accessKeySecret = "VMfoqfNr2TpdzQVc9sLIOmSt5f5rhN";
        String accessKeySecret = "Cgd3RZYjbeyBehK0CyaIekvuFER5yC";
        String bucketName = "niit-zyj";
        String filePath = "CloudMusic/";
        String fileName = file.getName();
        String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.indexOf("."));
        //创建OSSClient实例
//        OSSClient ossClient  = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        OSS ossClient  = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        //上传文件到指定位置，并使用UUID更名
        ossClient.putObject(bucketName,filePath + newFileName,file);
        //拼接URL
        String url = "https://niit-zyj.oss-cn-beijing.aliyuncs.com/" + filePath + newFileName;
        ossClient.shutdown();
        return url;
    }
}