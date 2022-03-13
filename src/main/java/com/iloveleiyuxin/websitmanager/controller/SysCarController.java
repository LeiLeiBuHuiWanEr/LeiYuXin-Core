package com.iloveleiyuxin.websitmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.common.CodeEnum;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.common.exception.LackParamException;
import com.iloveleiyuxin.websitmanager.entity.SysCar;
import com.iloveleiyuxin.websitmanager.vo.CarVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        String userName = req.getParameter("username");
        List<CarVo> list = sysCarService.selectList(new QueryWrapper<SysCar>().eq("username",userName));
        if(list.size() == 0){
            return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"查询结果为空");
        }
        return Response.succ(list);
    }

    @GetMapping("info/selectByNumber")
    public Response selectByNumber(){
        String userNo = req.getParameter("carNumber");
        if (userNo == null || userNo.equals("")){
            throw new LackParamException("缺失参数carNumber");
        }
        CarVo carVo = sysCarService.selectOneVo(userNo);
        Assert.notNull(carVo,"没有查询到结果");
        return Response.succ(carVo);
    }

    @PreAuthorize("hasAuthority('ROLE_999')")
    @GetMapping("operate/deleteByNumber")
    public Response delByNumber(){
        String userNo = req.getParameter("carNumber");
        if (userNo == null || userNo.equals("")){
            throw new LackParamException("缺失参数carNumber");
        }
        sysCarService.remove(new QueryWrapper<SysCar>().eq("carNo",userNo));
        return Response.succ("");
    }

    @GetMapping("operate/change")
    public Response change(){
        String number = req.getParameter("carNumber");
        if (number == null || number.equals("")){
            throw new LackParamException("缺失参数carNumber");
        }
        String brand = req.getParameter("brand");
        String color = req.getParameter("color");
        String fee = req.getParameter("fee");
        String state = req.getParameter("state");

        SysCar sysCar = new SysCar(number,null,color,brand,state == null ? null:Integer.valueOf(state),fee == null ? null: BigDecimal.valueOf(Double.parseDouble(fee)));

        sysCarService.changeCar(sysCar);

        return Response.succ("");
    }

}
