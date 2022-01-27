package com.iloveleiyuxin.websitmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.common.CodeEnum;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.entity.SysUser;
import com.iloveleiyuxin.websitmanager.service.ISysUserService;
import com.iloveleiyuxin.websitmanager.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TestController extends BaseController{

    @Autowired
    ISysUserService sysUserService;



    @Autowired
    RedisUtils redisUtil;

    @GetMapping("/test/t")
    public Response test() {
        return Response.succ(sysHouseService.addHouse("105","1","101",82.2,2.50));
    }

    @GetMapping("test/redis")
    public Response testRedis() {
        return Response.succ(redisUtil.get("GrantedAuthority:张婉婷"));
    }

    // 普通用户、超级管理员
    @GetMapping("/test/pass")
    public Response pass() {
        String st = req.getParameter("id");

        // 加密后密码
        String password = bCryptPasswordEncoder.encode(st);

        boolean matches = bCryptPasswordEncoder.matches(st, password);

        System.out.println(st);
        System.out.println("匹配结果：" + matches);

        return Response.succ(password);
    }

}
