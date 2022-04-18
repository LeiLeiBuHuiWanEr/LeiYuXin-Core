package com.iloveleiyuxin.websitmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.iloveleiyuxin.websitmanager.common.CodeEnum;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.common.exception.LackParamException;
import com.iloveleiyuxin.websitmanager.entity.SysGeli;
import com.iloveleiyuxin.websitmanager.vo.GeLiVo;
import org.springframework.web.bind.annotation.*;

import com.iloveleiyuxin.websitmanager.controller.BaseController;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-03-16
 */
@RestController
@RequestMapping("/sys-geli")
public class SysGeliController extends BaseController {
    @PostMapping("info/mapSelect")
    public Response MapSelectVo(@RequestBody Map<String,String> filterMap){
        List<GeLiVo> list = sysGeliService.mapSelectVo(filterMap);
        if(filterMap.size()==0){
            return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"查询结果为空");
        }
        return Response.succ(list);
    }
    /**
     * 根据传参新增一个记录
     * @return
     */
    @GetMapping("operate/insert")
    public Response insert(){
        String userId = req.getParameter("id");
        String description = req.getParameter("description");
        String type = req.getParameter("type");
        String reason = req.getParameter("reason");
        String count = req.getParameter("count");
        String rnaTest = req.getParameter("rnatest");
        String lastPlace = req.getParameter("lastPlace");

        if(userId == null || type == null || count == null){
            throw new LackParamException("缺失参数");
        }
        if(rnaTest == null){
            rnaTest = "未检测";
        }
        if(lastPlace == null){
            lastPlace = "130203";
        }

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(Integer.parseInt(count));

        SysGeli sysGeli = new SysGeli(Integer.valueOf(userId),description,Integer.valueOf(type),
                reason,Integer.valueOf(count),start,end,rnaTest,Integer.valueOf(lastPlace));

        sysGeliService.save(sysGeli);

        return Response.succ("");
    }

    /**
     * 根据filterMap新增一个记录
     * @param filterMap
     * @return
     */
    @GetMapping("operate/mapInsert")
    public Response mapInsert(@RequestBody Map<String,Object> filterMap){
        if(!filterMap.containsKey("gelidatecount")||!filterMap.containsKey("gelitype")|| !filterMap.containsKey("userid")){
            throw new LackParamException("缺失参数");
        }

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(Integer.parseInt((String) filterMap.get("count")));
        filterMap.put("begindate",start);
        filterMap.put("enddate",end);

        String json;
        try {
            json = objectMapper.writeValueAsString(filterMap);
        } catch (JsonProcessingException e) {
            return Response.fail(CodeEnum.LEIYUXIN_FALLEN_IN_LOVE,"JSON转换异常");
        }
        try {
            sysGeliService.save(objectMapper.readValue(json,SysGeli.class));
        } catch (JsonProcessingException e) {
            return Response.fail(CodeEnum.LEIYUXIN_FALLEN_IN_LOVE,"JSON转换异常");
        }
        return Response.succ("");
    }

    @GetMapping("info/selectByUser")
    public Response selectById(){
        String id = req.getParameter("id");
        if(id==null){
            throw new LackParamException("缺失参数id");
        }
        QueryWrapper<SysGeli> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysGeli::getGeliuser,id);

        List<GeLiVo> result = sysGeliService.selectVo(queryWrapper);
        return Response.succ(result);
    }

}
