package com.iloveleiyuxin.websitmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.entity.SysUnit;
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
 * @since 2022-01-23
 */
@RestController
@RequestMapping("/sys-unit")
public class SysUnitController extends BaseController {
    @GetMapping("info/{building}")
    public Response getUnitByBuilding(@PathVariable("building") String building){
        QueryWrapper<SysUnit> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUnit::getBuilding,building);
        return Response.succ(sysUnitService.list(wrapper));
    }


}
