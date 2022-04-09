package com.iloveleiyuxin.websitmanager.controller.echarts;

import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("charts/sys-building")
public class BuildingCharts extends BaseController {
    @GetMapping("/buildingCapacityInfo")
    public Response buildingCapacityInfo(){
        return Response.succ(sysBuildingService.buildingCapacityInfo());
    }
}
