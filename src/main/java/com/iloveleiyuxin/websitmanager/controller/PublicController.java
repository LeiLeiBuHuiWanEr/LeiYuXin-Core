package com.iloveleiyuxin.websitmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.common.CodeEnum;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.entity.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/public")
public class PublicController extends BaseController{
    @GetMapping("operate/addAUser")
    public Response addAUser(){
        String userName = req.getParameter("username");
        String password = bCryptPasswordEncoder.encode(req.getParameter("password"));
        String sex = req.getParameter("sex");
        if(!sex.equals("男")&&!sex.equals("女")){
            return Response.fail(CodeEnum.PARAM_NOT_VALID,"性别只能为男或者女");
        }
        LocalDate userBirthday = LocalDate.parse(req.getParameter("birthday"),formatter);
        String userPhone = req.getParameter("phone");
        SysUser user = new SysUser(userName,password,sex,false,userBirthday,userPhone,2);
        try {
            sysUserService.save(user);
        }catch (Exception e){
            return Response.fail(CodeEnum.ERROR_QUERYING_DATABASE,"数据库操作失败");
        }
        return Response.succ(sysUserService.getOne(new QueryWrapper<SysUser>().eq("userName", userName)));
    }

}
