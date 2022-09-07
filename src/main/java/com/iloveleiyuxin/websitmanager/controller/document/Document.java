package com.iloveleiyuxin.websitmanager.controller.document;

import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/document")
public class Document extends BaseController {
    @GetMapping("getDocx")
    public void getDocx() throws IOException {
        File f = new File("D:\\WORKS\\testDocx.docx");
        if(!f.exists()){
            throw new FileNotFoundException("文件没有找到！");
        }
        resp.setContentType("application/octet-stream");
        FileInputStream fis = new FileInputStream(f);
        byte[] data = new byte[(int)f.length()];
//        int length = fis.read(data);
        fis.close();
        OutputStream ops = resp.getOutputStream();
        ops.write(data);
        ops.flush();
        ops.close();
    }
}
