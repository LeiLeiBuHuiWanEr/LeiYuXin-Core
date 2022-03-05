package com.iloveleiyuxin.websitmanager.controller;


import com.iloveleiyuxin.websitmanager.common.Const;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.entity.SysHealthreport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-02-20
 */
@RestController
@RequestMapping("/sys-healthreport")
public class SysHealthreportController extends BaseController {

    //新增一条健康报告数据
    @GetMapping("operate/newReport")
    public Response newReport(){
        String userId = req.getParameter("userId");
        String description = req.getParameter("description");
        Double bodyTemperature = Double.parseDouble(req.getParameter("bodyTemperature"));
        String rnaTest = req.getParameter("rnaTest");

        Integer needQuantine = 0;
        if(bodyTemperature > Const.ALERT_BODY_TEMPERATURE || rnaTest.equals(Const.ERROR_RNA_TEST_RESULT)){
            needQuantine = 1;
        }

        SysHealthreport sysHealthreport = new SysHealthreport
                (Integer.parseInt(userId),description, LocalDateTime.now(),bodyTemperature,rnaTest,needQuantine);

        sysHealthreportService.newReport(sysHealthreport);
        return Response.succ("");
    }

}
