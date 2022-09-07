package com.iloveleiyuxin.websitmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.common.exception.LackParamException;
import com.iloveleiyuxin.websitmanager.entity.BaseEntity;
import com.iloveleiyuxin.websitmanager.entity.SysService;
import com.iloveleiyuxin.websitmanager.vo.ServiceVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.iloveleiyuxin.websitmanager.controller.BaseController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-04-09
 */
@RestController
@RequestMapping("/sys-service")
public class SysServiceController extends BaseController {
    @PostMapping("info/mapSelect")
    public Response mapSelect(@RequestBody(required = true) Map<String,String> filterMap){
        List<ServiceVo> result = sysServiceService.mapSelectVo(filterMap);
        if(result.size()==0){
            return Response.fail("查询结果为空");
        }

        return Response.succ(result);
    }

    @PostMapping("operate/mapChange")
    public Response mapChange(@RequestBody(required = true)Map<String,Object> filterMap){
        if(!filterMap.containsKey("id")){
            throw new LackParamException("缺失参数ID");
        }
        Integer id = Integer.parseInt(filterMap.get("id").toString());
        SysService sysService = sysServiceService.getOne(new QueryWrapper<SysService>().lambda().eq(BaseEntity::getId,id));
        if(filterMap.get("servicestate")!=null && filterMap.get("servicestate")!=""){
            if(filterMap.get("servicestate").equals(2)){
                sysService.setEnddate(LocalDateTime.now());
            }
            sysService.setServicestate((Integer) filterMap.get("servicestate"));
        }
        if(filterMap.get("servicescore")!=null && filterMap.get("servicescore")!=""){
            sysService.setServicescore((Integer) filterMap.get("servicescore"));
        }
        sysServiceService.change(sysService);
        return Response.succ(sysService);
    }

}
