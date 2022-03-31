package com.iloveleiyuxin.websitmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.common.CodeEnum;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.common.exception.LackParamException;
import com.iloveleiyuxin.websitmanager.entity.SysCar;
import com.iloveleiyuxin.websitmanager.vo.CarVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import com.iloveleiyuxin.websitmanager.controller.BaseController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        String carNo = req.getParameter("carno");
        String color = req.getParameter("color");
        Integer owner = Integer.valueOf(req.getParameter("owner"));
        String carBrand = req.getParameter("brand");
        BigDecimal fee = BigDecimal.valueOf(Double.parseDouble(req.getParameter("fee")));
        String carTC = req.getParameter("cartypecolor");

        SysCar car = new SysCar(carNo,owner,color,carBrand,1,fee,carTC);
        sysCarService.insertCar(car);

        return Response.succ("");
    }

    @PostMapping("info/professionalSearch")
    public Response professionalSearch(@RequestBody(required = false)Map<String,String> filterMap){
        List<CarVo> list = sysCarService.selectByMap(filterMap);
        if(list.size()==0){
            return Response.fail(CodeEnum.EMPTY_LIST_OR_MAP,"没有查询到结果");
        }
        return Response.succ(list);
    }

    @GetMapping("info/selectByOwner")
    public Response selectByOwner(){
        String userName = req.getParameter("username");
        List<CarVo> list = new ArrayList<>();
        if (userName == null || userName.equals("")){
            list = sysCarService.selectList(new QueryWrapper<SysCar>());
        }else{
            list = sysCarService.selectList(new QueryWrapper<SysCar>().eq("username",userName));
        }
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

    @PostMapping("operate/changeByMap")
    public Response updateByMap(@RequestBody(required = true) Map<String,String> filterMap){
        String number = filterMap.get("carno");
        SysCar sysCar = new SysCar();
        if (number == null || number.equals("")){
            throw new LackParamException("缺失参数carno");
        }
        sysCar.setCarno(number);
        String owner = filterMap.get("carowner");
        if(owner != null && !owner.equals("")){
            sysCar.setCarowner(Integer.parseInt(owner));
        }
        sysCar.setCarbrand(filterMap.get("carbrand"));
        sysCar.setCarcolor(filterMap.get("carcolor"));
        String fee = filterMap.get("fee");
        if (fee != null && !fee.equals("")){
            sysCar.setFee(BigDecimal.valueOf(Double.parseDouble(fee)));
        }
        String state = filterMap.get("carstate");
        if (state != null && !state.equals("")){
            sysCar.setCarstate(Integer.valueOf(state));
        }
        sysCar.setCartypecolor(filterMap.get("cartypecolor"));

        //SysCar sysCar = new SysCar(number,Integer.parseInt(owner),color,brand,state == null ? null:Integer.valueOf(state),fee == null ? null: BigDecimal.valueOf(Double.parseDouble(fee)),tc);

        sysCarService.changeCar(sysCar);

        return Response.succ("");
    }

    @GetMapping("operate/change")
    public Response change(){
        String number = req.getParameter("carno");
        if (number == null || number.equals("")){
            throw new LackParamException("缺失参数carno");
        }
        String owner = req.getParameter("carowner");
        String brand = req.getParameter("carbrand");
        String color = req.getParameter("carcolor");
        String fee = req.getParameter("fee");
        String state = req.getParameter("carstate");
        String tc = req.getParameter("cartypecolor");

        SysCar sysCar = new SysCar(number,Integer.parseInt(owner),color,brand,state == null ? null:Integer.valueOf(state),fee == null ? null: BigDecimal.valueOf(Double.parseDouble(fee)),tc);

        sysCarService.changeCar(sysCar);

        return Response.succ("");
    }

}
