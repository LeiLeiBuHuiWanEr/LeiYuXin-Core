package com.iloveleiyuxin.websitmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iloveleiyuxin.websitmanager.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iloveleiyuxin.websitmanager.httpClient.HttpApi;
import com.iloveleiyuxin.websitmanager.service.ISysRoleService;
import com.iloveleiyuxin.websitmanager.service.ISysUserService;
import com.iloveleiyuxin.websitmanager.utils.JwtUtils;
import com.iloveleiyuxin.websitmanager.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;

public class BaseController {
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public HttpServletRequest req;

    @Autowired
    public HttpServletResponse resp;

    @Autowired
    public RedisUtils redisUtil;

    @Autowired
    public ISysUserService sysUserService;

    @Autowired
    public ISysRoleService sysRoleService;

    @Autowired
    public ISysBuildingService sysBuildingService;

    @Autowired
    public ISysHouseService sysHouseService;

    @Autowired
    public ISysUnitService sysUnitService;

    @Autowired
    public ISysCarService sysCarService;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ICliUserService cliUserService;

    @Autowired
    public JwtUtils jwtUtils;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public HttpApi http;

    @Autowired
    public ISysHealthreportService sysHealthreportService;

    @Autowired
    public ISysGeliService sysGeliService;

    @Autowired
    public ISysRegionService sysRegionService;

    @Autowired
    public ISysServiceService sysServiceService;
}
