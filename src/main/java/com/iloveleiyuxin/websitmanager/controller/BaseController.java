package com.iloveleiyuxin.websitmanager.controller;

import com.iloveleiyuxin.websitmanager.service.*;
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
    ISysBuildingService sysBuildingService;

    @Autowired
    ISysHouseService sysHouseService;

    @Autowired
    ISysUnitService sysUnitService;

    @Autowired
    ISysCarService sysCarService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ICliUserService cliUserService;

    @Autowired
    JwtUtils jwtUtils;

}
