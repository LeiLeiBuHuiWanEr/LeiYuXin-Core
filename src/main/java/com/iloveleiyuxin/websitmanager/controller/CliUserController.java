package com.iloveleiyuxin.websitmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.iloveleiyuxin.websitmanager.common.CodeEnum;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.common.exception.LackParamException;
import com.iloveleiyuxin.websitmanager.entity.CliUser;
import com.iloveleiyuxin.websitmanager.entity.SysGeli;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-28
 */
@RestController
@RequestMapping("/cli-user")
public class CliUserController extends BaseController {
    @PostMapping("operate/insert")
    public Response addCliUser(){
        String userName = req.getParameter("userName");
        String nickName = req.getParameter("nickName");
        String password = req.getParameter("password");
        String avatar = req.getParameter("avatar");
        String sex = req.getParameter("sex");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String cliRole = req.getParameter("role");
        String locate = req.getParameter("locate");
        String healthState = req.getParameter("healthState");
        String quarantineState = req.getParameter("quarantineState");
        String permanentResidence = req.getParameter("permanentResidence");
        String lowIncome = req.getParameter("lowIncome");
        String idCard = req.getParameter("idCard");
        /**
         * 不允许为空的项目，如果为空就会直接返回缺失参数的异常
         */
        boolean bool = userName == null || password == null || sex == null || locate == null || phone == null;
        if(bool){
            return Response.fail(CodeEnum.NEED_PARAM,"缺失必要的参数");
        }
        if (!sex.equals("男") && !sex.equals("女")){
            throw new IllegalArgumentException("性别只能为男或者女！");
        }
        /**
         * 无关紧要的参数为空时，对默认数据进行一个简单处理
         * 昵称默认为用户名，生日默认为代码编写日期：2022年1月31日,角色默认为2（普通权限）,健康状态为0，常住状态为1，低保默认为0（不享受）
         */
        if (nickName == null){
            nickName = userName;
        }
        if (birthday == null){
            birthday = "2022-01-31";
        }
        if (cliRole == null){
            cliRole = "801";
        }
        if (healthState == null){
            healthState = "0";
        }
        if (quarantineState == null){
            quarantineState = "0";
        }
        if (permanentResidence == null){
            permanentResidence = "1";
        }
        if (lowIncome == null){
            lowIncome = "0";
        }

        password = bCryptPasswordEncoder.encode(password);

        CliUser cliUser = new CliUser(
                userName,nickName,avatar,password,sex,LocalDate.parse(birthday),phone,Integer.valueOf(cliRole),Integer.valueOf(locate),Integer.valueOf(healthState),
                Integer.valueOf(quarantineState),Integer.valueOf(permanentResidence),Integer.valueOf(lowIncome),LocalDate.now(),idCard
                );

        cliUserService.addCliUser(cliUser);
        return Response.succ(cliUser);
    }

    @PostMapping("operate/mapInsert")
    public Response addCliUserByMap(@RequestBody Map<String,Object> filterMap){
        if(!filterMap.containsKey("username")){
            return Response.fail(CodeEnum.NEED_PARAM,"缺失必要的参数[userName]");
        }else if(!filterMap.containsKey("userpassword")){
            return Response.fail(CodeEnum.NEED_PARAM,"缺失必要的参数[userpassword]");
        }else if(filterMap.get("sex")==null||(!filterMap.get("sex").equals("男")&&!filterMap.get("sex").equals("女"))){
            return Response.fail(CodeEnum.NEED_PARAM,"缺失正确参数[sex]");
        }else if(!filterMap.containsKey("locate")){
            return Response.fail(CodeEnum.NEED_PARAM,"缺失必要的参数[locate]");
        }else if(!filterMap.containsKey("phone")){
            return Response.fail(CodeEnum.NEED_PARAM,"缺失必要的参数[phone]");
        }

        if(!filterMap.containsKey("nickname")){
            filterMap.put("nickname",filterMap.get("username"));
        }

        if(filterMap.get("birthday")!=null){
            filterMap.put("birthday",LocalDate.parse(filterMap.get("birthday").toString()));
        }
        filterMap.put("userpassword",bCryptPasswordEncoder.encode(filterMap.get("userpassword").toString()));
        filterMap.put("registerdate",LocalDate.now());

        String json;
        try {
            json = objectMapper.writeValueAsString(filterMap);
        } catch (JsonProcessingException e) {
            return Response.fail(CodeEnum.LEIYUXIN_FALLEN_IN_LOVE,"JSON转换异常");
        }
        try {
            cliUserService.addCliUser(objectMapper.readValue(json,CliUser.class));
        } catch (JsonProcessingException e) {
            return Response.fail(CodeEnum.LEIYUXIN_FALLEN_IN_LOVE,"JSON转换异常");
        }
        return Response.succ("");
    }

    @PostMapping("operate/mapChange")
    public Response mapChange(@RequestBody(required = true)Map<String,String> filterMap){
        String id = filterMap.get("id");
        CliUser currentUser = cliUserService.getById(id);
        Assert.notNull(currentUser,"查无此人！");
        String userName = filterMap.get("username");
        if (userName != null && !userName.equals("")){
            currentUser.setUsername(userName);
        }
        String pwd = filterMap.get("password");
        if (pwd != null && !pwd.equals("")){
            currentUser.setUserpassword(bCryptPasswordEncoder.encode(pwd));
        }
        String avt = filterMap.get("avatar");
        if (avt != null && !avt.equals("")){
            currentUser.setAvatar(avt);
        }
        String idc = filterMap.get("idcard");
        if (avt != null && !avt.equals("")){
            currentUser.setIdcard(idc);
        }
        String nickName = filterMap.get("nickname");
        if (nickName != null && !nickName.equals("")){
            currentUser.setNickname(nickName);
        }
        String sex = filterMap.get("sex");
        if (sex != null && !sex.equals("")){
            if (sex.equals("男")||sex.equals("女")){
                currentUser.setSex(sex);
            }else throw new IllegalArgumentException("性别只能是男或者女！");
        }
        String birthday = filterMap.get("birthday");
        if (birthday != null && !birthday.equals("")){
            try {
                currentUser.setBirthday(LocalDate.parse(birthday));
            }catch (DateTimeParseException e){
                throw new IllegalArgumentException("日期格式不正确！");
            }
        }
        String phone = filterMap.get("phone");
        if (phone != null && !phone.equals("")){
            currentUser.setPhone(phone);
        }
        String cliRole = filterMap.get("role");
        if (cliRole != null && !cliRole.equals("")){
            currentUser.setClirole(Integer.parseInt(cliRole));
        }
        String locate = filterMap.get("locate");
        if (locate != null && !locate.equals("")){
            currentUser.setLocate(Integer.parseInt(locate));
        }
        String healthState = filterMap.get("healthstate");
        if (healthState != null && !healthState.equals("")){
            currentUser.setHealthstate(Integer.parseInt(healthState));
        }
        String quarantineState = filterMap.get("quarantinestate");
        if (quarantineState != null && !quarantineState.equals("")){
            currentUser.setQuarantinestate(Integer.parseInt(quarantineState));
        }
        String permanentResidence = filterMap.get("permanentresidence");
        if (permanentResidence != null && !permanentResidence.equals("")){
            currentUser.setPermanentresidence(Integer.parseInt(permanentResidence));
        }
        String lowIncome = filterMap.get("lowincome");
        if (lowIncome != null && !lowIncome.equals("")){
            currentUser.setLowincome(Integer.parseInt(lowIncome));
        }
        cliUserService.updateCliUser(currentUser);
        return Response.succ(currentUser);
    }

    @Deprecated
    @GetMapping("operate/change")
    public Response changeCliUser(){
        String id = req.getParameter("id");
        CliUser currentUser = cliUserService.getById(id);
        Assert.notNull(currentUser,"查无此人！");
        String userName = req.getParameter("username");
        if (userName != null && !userName.equals("")){
            currentUser.setUsername(userName);
        }
        String pwd = req.getParameter("password");
        if (pwd != null && !pwd.equals("")){
            currentUser.setUserpassword(bCryptPasswordEncoder.encode(pwd));
        }
        String nickName = req.getParameter("nickname");
        if (nickName != null && !nickName.equals("")){
            currentUser.setNickname(nickName);
        }
        String sex = req.getParameter("sex");
        if (sex != null && !sex.equals("")){
            if (sex.equals("男")||sex.equals("女")){
                currentUser.setSex(sex);
            }else throw new IllegalArgumentException("性别只能是男或者女！");
        }
        String birthday = req.getParameter("birthday");
        if (birthday != null && !birthday.equals("")){
            try {
                currentUser.setBirthday(LocalDate.parse(birthday));
            }catch (DateTimeParseException e){
                throw new IllegalArgumentException("日期格式不正确！");
            }
        }
        String phone = req.getParameter("phone");
        if (phone != null && !phone.equals("")){
            currentUser.setPhone(phone);
        }
        String cliRole = req.getParameter("role");
        if (cliRole != null && !cliRole.equals("")){
            currentUser.setClirole(Integer.parseInt(cliRole));
        }
        String locate = req.getParameter("locate");
        if (locate != null && !locate.equals("")){
            currentUser.setLocate(Integer.parseInt(locate));
        }
        String healthState = req.getParameter("healthstate");
        if (healthState != null && !healthState.equals("")){
            currentUser.setLocate(Integer.parseInt(healthState));
        }
        String quarantineState = req.getParameter("quarantinestate");
        if (quarantineState != null && !quarantineState.equals("")){
            currentUser.setLocate(Integer.parseInt(quarantineState));
        }
        String permanentResidence = req.getParameter("permanentresidence");
        if (permanentResidence != null && !permanentResidence.equals("")){
            currentUser.setLocate(Integer.parseInt(permanentResidence));
        }
        String lowIncome = req.getParameter("lowincome");
        if (lowIncome != null && !lowIncome.equals("")){
            currentUser.setLocate(Integer.parseInt(lowIncome));
        }
        System.out.println(currentUser.getLocate());
        cliUserService.updateCliUser(currentUser);
        return Response.succ(currentUser);
    }

    @GetMapping("info/{id}")
    public Response selectByID(@PathVariable Integer id){
        CliUser cliUser = cliUserService.getById(id);
        Assert.notNull(cliUser,"查无此人！");
        return Response.succ(cliUser);
    }

    @GetMapping("info/selectAll")
    public Response selectAll(){
        List<CliUser> list = cliUserService.list();
        return Response.succ(list);
    }

    @PostMapping("info/select/map")
    public Response select(@RequestBody(required = false) Map filterMap){
        List<CliUser> list = cliUserService.listByMap(filterMap);
        if (list.size() == 0){
            return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"没有查询到结果");
        }
        return Response.succ(list);
    }

    @PreAuthorize("hasAuthority('ROLE_999')")
    @GetMapping("operate/delete")
    public Response delete(){
        String id = req.getParameter("id");
        cliUserService.removeById(id);
        return Response.succ("");
    }

    /**
     * 疫情部分
     */
    /**
     * 查询当前隔离者列表
     * @return
     */
    @GetMapping("info/select/geli")
    public Response geLiList(){
        List<CliUser> resultList = cliUserService.list(new QueryWrapper<CliUser>().notIn("quarantineState",0));
        if (resultList == null || resultList.size()==0){
            return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"没有查询到结果");
        }
        return Response.succ(resultList);
    }

    @GetMapping("info/geliById")
    public Response geliById(){
        String id = req.getParameter("id");
        if(id == null){
            throw new LackParamException("缺失参数id");
        }
        boolean state = sysGeliService.isGeli(Integer.parseInt(id));

        return Response.succ(state);
    }

    @GetMapping("info/select/jiankang")
    public Response buJianKangList(){
        List<CliUser> resultList;
        if(req.getParameter("type").equals("99")){
            resultList = cliUserService.list(new QueryWrapper<CliUser>().eq("healthState",99));
        }else{
            resultList = cliUserService.list(new QueryWrapper<CliUser>().notIn("healthState",0));
        }

        if (resultList == null || resultList.size()==0){
            return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"没有查询到结果");
        }
        return Response.succ(resultList);
    }

    @GetMapping("operate/resetHealthState")
    public Response resetHealthState(){
        String id = req.getParameter("id");
        Assert.notNull(id,"缺失参数");
        CliUser user = cliUserService.getById(id);
        Assert.notNull(user,"查无此人");
        user.setHealthstate(0);
        cliUserService.updateById(user);
        return Response.succ("");
    }

}
