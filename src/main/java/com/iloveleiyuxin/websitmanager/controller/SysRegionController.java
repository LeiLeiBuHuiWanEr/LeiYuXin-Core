package com.iloveleiyuxin.websitmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.entity.SysRegion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.iloveleiyuxin.websitmanager.controller.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-03-16
 */
@RestController
@RequestMapping("/sys-region")
public class SysRegionController extends BaseController {
    /**
     * 获取省级行政区列表
     */
    @GetMapping("/info/getProvince")
    public Response getProvince(){
        QueryWrapper<SysRegion> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRegion::getRegionLevel,1);
        return Response.succ(sysRegionService.list(queryWrapper));
    }

    /*
        获取地市级行政区
     */
    @GetMapping("info/getCity/{province}")
    public Response getCity(@PathVariable("province")Integer province){
        QueryWrapper<SysRegion> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRegion::getParentRegionCode,province);
        return Response.succ(sysRegionService.list(queryWrapper));
    }

    /*
        获取区县级行政区
     */
    @GetMapping("info/getDistrict/{city}")
    public Response getDistrict(@PathVariable("city")Integer city){
        QueryWrapper<SysRegion> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRegion::getParentRegionCode,city);
        return Response.succ(sysRegionService.list(queryWrapper));
    }


}
