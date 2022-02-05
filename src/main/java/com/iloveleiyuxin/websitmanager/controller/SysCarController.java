package com.iloveleiyuxin.websitmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.common.CodeEnum;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.entity.SysCar;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.iloveleiyuxin.websitmanager.controller.BaseController;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-02-03
 */
@RestController
@RequestMapping("/sys-car")
public class SysCarController extends BaseController {

    @GetMapping("operate/insert")
    public Response insertCar(){
        String carNo = req.getParameter("carNo");
        String color = req.getParameter("color");
        Integer owner = Integer.valueOf(req.getParameter("owner"));
        String carBrand = req.getParameter("brand");
        BigDecimal fee = BigDecimal.valueOf(Double.parseDouble(req.getParameter("fee")));

        SysCar car = new SysCar(carNo,owner,color,carBrand,1,fee);
        sysCarService.insertCar(car);

        return Response.succ("");
    }

    @GetMapping("info/selectByOwner")
    public Response selectByOwner(){
        String userNo = req.getParameter("userNo");
        List<SysCar> list = sysCarService.list(new QueryWrapper<SysCar>().eq("carOwner",userNo));
        if(list.size() == 0){
            return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"查询结果为空");
        }
        return Response.succ(list);
    }

    @GetMapping("info/selectByNumber")
    public Response selectByNumber(){
        String userNo = req.getParameter("carNumber");
        SysCar sysCar = sysCarService.getOne(new QueryWrapper<SysCar>().eq("carNo",userNo));
        Assert.notNull(sysCar,"没有查询到结果");
        return Response.succ(sysCar);
    }

}
