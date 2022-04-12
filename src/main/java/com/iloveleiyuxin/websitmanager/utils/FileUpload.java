package com.iloveleiyuxin.websitmanager.utils;

import cn.hutool.core.io.FileUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 上传文件(已弃用)
 * 仅能向本服务器上传，已经提供了上传至文件服务器的接口
 */
@Deprecated
public class FileUpload {
    private final String url = "D:/Files";

    public String upload(@RequestBody(required = true) MultipartFile file) throws IOException {
        File target = new File(url);
        System.out.println(url);
        if(!target.exists()){
            target.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String prefix = fileName.substring(0,fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = sdf.format(date);
        String saveFileName = prefix + timeStamp +suffix;

        FileOutputStream outputStream = new FileOutputStream(url+"/"+saveFileName);
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();

        return "http://yapi.iloveleiyuxin.com:8080/File/"+saveFileName;
    }
}
