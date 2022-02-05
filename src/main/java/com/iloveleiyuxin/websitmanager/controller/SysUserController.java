package com.iloveleiyuxin.websitmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iloveleiyuxin.websitmanager.common.CodeEnum;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.common.exception.LeiYuXinFallenInLoveException;
import com.iloveleiyuxin.websitmanager.entity.SysUser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-02
 */
@Slf4j
@RestController
@RequestMapping("/sys-user")
public class SysUserController extends BaseController {



    @GetMapping("info/{id}")
    public Response info(@PathVariable("id") Integer id) {

        SysUser sysUser = sysUserService.getById(id);
        Assert.notNull(sysUser, "查无此人！");
//        List<SysRole> roles = sysRoleService.getRoleByUserId(id);
//
//        sysUser.setSysRoles(roles);
        return Response.succ(sysUser);
    }

    @GetMapping("info/listUser")
    public Response getUserList(){
        String current = req.getParameter("current");
        String size = req.getParameter("size");
        if(current != null && size != null){
            IPage<SysUser> resultPage = new Page<>(Integer.parseInt(current),Integer.parseInt(size));
            resultPage = sysUserService.page(resultPage,new QueryWrapper<SysUser>().in("userRole",2));
            if(resultPage.getRecords().size() == 0){
                return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"当前页溢出！！");
            }
            return Response.succ(resultPage);
        }
        else{
            List<SysUser> resultList = sysUserService.getUserList();
            if(resultList.size() == 0){
                return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"查询结果为空!!",resultList);
            }
            return Response.succ(resultList);
        }

    }

    @GetMapping("info/listAll")
    public Response getAllUserAndAdminList(){
        String current = req.getParameter("current");
        String size = req.getParameter("size");
        if(current != null && size != null){
            IPage<SysUser> resultPage = new Page<>(Integer.parseInt(current),Integer.parseInt(size));
            resultPage = sysUserService.page(resultPage);
            if(resultPage.getRecords().size() == 0){
                return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"当前页溢出!!");
            }
            return Response.succ(resultPage);
        }else{
            List<SysUser> resultList = sysUserService.list();
            if(resultList.size() == 0){
                return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"查询结果为空!!",resultList);
            }
            return Response.succ(resultList);
        }

    }

    @PreAuthorize("hasAnyAuthority('ROLE_1','ROLE_999')")
    @GetMapping("/operate/identify")
    public Response identifyUnableUser(){
        String token = req.getHeader("Authorization");
        return Response.succ("");
    }

    @PreAuthorize("hasAuthority('ROLE_1')")
    @GetMapping("t1")
    public Response test(){
        String token = req.getHeader("Authorization");
        String user = Jwts.parser()
                .setSigningKey("ji8n3439n439n43ld9ne9343fdfer49h")
                .parseClaimsJws(token)
                .getBody().getSubject();

        return Response.succ(user);
    }
    @PreAuthorize("hasAuthority('ROLE_2')")
    @GetMapping("t2")
    public Response test2(){
        return new Response().succ("我爱张宇馨");
    }

    @PreAuthorize("hasAuthority('ROLE_1')")
    @PostMapping("operate/addSuperAdmin")
    public Response addASuperAdmin(){
        String userName = req.getParameter("username");
        String password = bCryptPasswordEncoder.encode(req.getParameter("password"));
        String sex = req.getParameter("sex");
        if(!sex.equals("男")&&!sex.equals("女")){
            return Response.fail(CodeEnum.PARAM_NOT_VALID,"性别只能为男或者女");
        }
        LocalDate userBirthday = LocalDate.parse(req.getParameter("birthday"),formatter);
        String userPhone = req.getParameter("phone");
        SysUser user = new SysUser(userName,password,sex,true,userBirthday,userPhone,999);
        try {
            sysUserService.save(user);
        }catch (Exception e){
         return Response.fail(CodeEnum.ERROR_QUERYING_DATABASE,"数据库操作失败");
        }
        return Response.succ(sysUserService.getOne(new QueryWrapper<SysUser>().eq("userName", userName)));
    }

    @PostMapping("operate/changePassword")
    public Response changePassword(){
        String passwordNew = req.getParameter("newPassword");
        String passwordOld = req.getParameter("oldPassword");
        if(null == passwordNew || passwordNew.equals("")){
            return Response.fail(CodeEnum.NEED_PARAM,"缺少参数");
        }
        String token = req.getHeader("Authorization");
        String user = Jwts.parser()
                .setSigningKey("ji8n3439n439n43ld9ne9343fdfer49h")
                .parseClaimsJws(token)
                .getBody().getSubject();
        SysUser sysUser = sysUserService.getByUsername(user);
        if(!bCryptPasswordEncoder.matches(passwordOld, sysUser.getUserpassword())){
            throw new LeiYuXinFallenInLoveException("密码错误");
        }
        sysUser.setUserpassword(bCryptPasswordEncoder.encode(passwordNew));
        sysUserService.save(sysUser);
        log.info(sysUser.getUsername()+"的密码已修改成功！新的密码为"+passwordNew);
        return Response.succ("");
    }
}
