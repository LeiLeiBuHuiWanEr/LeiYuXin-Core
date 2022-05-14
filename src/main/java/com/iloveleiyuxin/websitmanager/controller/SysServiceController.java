package com.iloveleiyuxin.websitmanager.controller;


import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.vo.ServiceVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.iloveleiyuxin.websitmanager.controller.BaseController;

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

}
