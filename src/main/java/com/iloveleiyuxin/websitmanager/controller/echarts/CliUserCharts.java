package com.iloveleiyuxin.websitmanager.controller.echarts;

import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.controller.BaseController;
import com.iloveleiyuxin.websitmanager.service.ICliUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("charts/cli-user")
public class CliUserCharts extends BaseController {
    @RequestMapping("healthState")
    public Response healthStateAnalysis() {
        List<Map<String, Object>> list = cliUserService.healthAnalysis();
        return Response.succ(list);
    }

    @RequestMapping("quarantineState")
    public Response quarantineAnalysis() {
        List<Map<String, Object>> list = cliUserService.quarantineAnalysis();
        return Response.succ(list);
    }

    @RequestMapping("permanentResidence")
    public Response permanentAnalysis() {
        List<Map<String, Object>> list = cliUserService.permanentAnalysis();
        return Response.succ(list);
    }

    @GetMapping("registerAnalysis")
    public Response registerAnalysis(){
        return Response.succ(cliUserService.registerAnalysis());
    }
}