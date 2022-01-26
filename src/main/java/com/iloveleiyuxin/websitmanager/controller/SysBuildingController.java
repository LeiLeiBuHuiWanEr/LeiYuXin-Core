package com.iloveleiyuxin.websitmanager.controller;


import com.iloveleiyuxin.websitmanager.common.CodeEnum;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.entity.SysBuilding;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.iloveleiyuxin.websitmanager.controller.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-23
 */
@RestController
@RequestMapping("/sys-building")
public class SysBuildingController extends BaseController {
    @GetMapping("/info/listAll")
    public Response listAllBuilding(){
        List<SysBuilding> buildingList = sysBuildingService.list();
        if(buildingList == null || buildingList.size()==0){
            return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"没有查到信息",null);
        }
        return Response.succ(buildingList);
    }

    @GetMapping("/info/{id}")
    public Response getOneBuilding(@PathVariable Integer id){
        SysBuilding result = sysBuildingService.getById(id);
        Assert.notNull(result, "无结果");
        return Response.succ(result);
    }

    @GetMapping("/info/counts")
    public Response getBuildingCounts(){
        return Response.succ(sysBuildingService.count());
    }

    @GetMapping("/operate/insertBuilding")
    public Response insertBuilding(){
        return Response.succ("");
    }

}
