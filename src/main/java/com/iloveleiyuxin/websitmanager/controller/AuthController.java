package com.iloveleiyuxin.websitmanager.controller;

import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.iloveleiyuxin.websitmanager.common.Const;
import com.iloveleiyuxin.websitmanager.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 验证Controller
 * @author LeiYuXin's Boyfriend
 */
@Slf4j
@RestController
public class AuthController extends BaseController{
    @Autowired
    private Producer producer;

    @GetMapping("/kaptcha")
    public Response kaptcha() throws IOException {
        String code = producer.createText();
        String key = UUID.randomUUID().toString();

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());

        // 存储到redis中
        redisUtil.hset(Const.KAPTCHA_KEY, key, code, 120);
        log.info("验证码 -- {} - {}", key, code);
        return Response.succ(
                MapUtil.builder()
                        .put("token", key)
                        .put("base64Img", base64Img)
                        .build()
        );
    }
}
