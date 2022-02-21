package com.iloveleiyuxin.websitmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iloveleiyuxin.websitmanager.httpClient.HttpApi;
import com.iloveleiyuxin.websitmanager.service.ISysRoleService;
import com.iloveleiyuxin.websitmanager.service.ISysUserService;
import com.iloveleiyuxin.websitmanager.utils.JwtUtils;
import com.iloveleiyuxin.websitmanager.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;

public class BaseController {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    HttpServletRequest req;

    @Autowired
    RedisUtils redisUtil;

    @Autowired
    ISysUserService sysUserService;

    @Autowired
    ISysRoleService sysRoleService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    HttpApi http;

    @Autowired
    ObjectMapper objectMapper;
}
