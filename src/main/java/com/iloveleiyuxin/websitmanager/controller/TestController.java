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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class TestController extends BaseController{

    @Autowired
    ISysUserService sysUserService;



    @Autowired
    RedisUtils redisUtil;

    @GetMapping("/test/t")
    public Response test() {
        return Response.succ(sysHouseService.updateHouse("10101101",82.2,2.50,"我爱雷雨鑫"));
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

    @PostMapping("test/map")
    public Response mapTest(@RequestBody Map map){
        String s = req.getParameter("userName");
        System.out.println(s);
        return Response.succ(map);
    }

}
