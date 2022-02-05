package com.iloveleiyuxin.websitmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iloveleiyuxin.websitmanager.common.CodeEnum;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.entity.SysHouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-23
 */
@Slf4j
@RestController
@RequestMapping("/sys-house")
public class SysHouseController extends BaseController {
    /**
     * 查询所有房屋，使用分页
     */
    @GetMapping("info/listAll")
    public Response getAllHouse(){
        String current = req.getParameter("current");
        String size = req.getParameter("size");
        if(current != null && size != null){
            IPage<SysHouse> resultPage = new Page<>(Integer.parseInt(current),Integer.parseInt(size));
            resultPage = sysHouseService.page(resultPage);
            if(resultPage.getRecords().size() == 0){
                return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"当前页溢出！！");
            }
            return Response.succ(resultPage.getRecords());
        }else{
            List<SysHouse> resultList = sysHouseService.list();
            if(resultList == null){
                return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"查询结果为空");
            }
            return Response.succ(resultList);
        }
    }

    /**
     * 新增一个房屋
     * @return
     */
    @GetMapping("operate/addHouse")
    public Response addHouse(){
        String buildingNo = req.getParameter("buildingNo");
        String unitNo = req.getParameter("unitNo");
        String houseNo = req.getParameter("houseNo");
        String area = req.getParameter("area");
        String fee = req.getParameter("fee");
        Double areaD;
        Double feeD;

        if(buildingNo == null || unitNo == null || houseNo == null){
            return Response.fail(CodeEnum.NEED_PARAM,"缺失参数");
        }

        try{
            areaD = Double.valueOf(area);
            feeD = Double.valueOf(fee);
        }catch (Exception e){
            return Response.fail(CodeEnum.PARAM_NOT_VALID,"参数格式不正确");
        }

        boolean bool = sysHouseService.addHouse(buildingNo,unitNo,houseNo,areaD,feeD);

        if(bool){
            return Response.succ("");
        }else {
            return Response.fail("添加失败");
        }
    }

    /**
     * 楼、单元、门牌号的房屋查询
     * @return 标准返回格式
     */
    @GetMapping("info/select")
    public Response selectByThreeKey(){
        String buildingNo = req.getParameter("buildingNo");
        String unitNo = req.getParameter("unitNo");
        String houseNo = req.getParameter("houseNo");

        if(buildingNo == null){
            return Response.fail(CodeEnum.NEED_PARAM,"缺失参数");
        }

        Map<String,Object> filterMap = new HashMap<>();

        /**
         * 仅包括楼的情况
         */
        if(unitNo == null && houseNo == null){
            filterMap.put("building",Integer.valueOf(buildingNo));
            List<SysHouse> result = sysHouseService.listByMap(filterMap);
            if (result == null || result.size() == 0){
                return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"查询结果为空！");
            }
            return Response.succ(result);
        }

        if(unitNo == null){
            return Response.fail(CodeEnum.PARAM_NOT_VALID,"错误的传参方式，缺失单元号，无法查询结果！");
        }

        if(unitNo.length()==1){
            unitNo = "0"+unitNo;
        }

        /**
         * 包括楼和单元的情况
         */
        if(houseNo == null){
            filterMap.put("unit",Integer.valueOf(buildingNo+unitNo));
            List<SysHouse> result = sysHouseService.listByMap(filterMap);
            if (result == null || result.size() == 0){
                return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"查询结果为空！");
            }
            return Response.succ(result);
        }

        /**
         * 楼号、单元、房间号精准查询
         */
        SysHouse result = sysHouseService.getById(Integer.valueOf(buildingNo+unitNo+houseNo));
        Assert.notNull(result,"没有查询到相应的结果");
        return Response.succ(result);

    }
        /**
        *  删除房间（敏感权限，仅能由超级管理员操作）
        */
    @PreAuthorize("hasAuthority('ROLE_999')")
    @GetMapping("/operate/deleteById")
    public Response delHouse(){
        String id = req.getParameter("id");
        if(id == null){
            return Response.fail(CodeEnum.NEED_PARAM,"缺失参数");
        }
        sysHouseService.removeHouse(id);

        return Response.succ("");

    }


}
