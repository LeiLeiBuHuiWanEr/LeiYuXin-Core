package com.iloveleiyuxin.websitmanager.controller;


import com.iloveleiyuxin.websitmanager.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.iloveleiyuxin.websitmanager.controller.BaseController;

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

    @GetMapping("operate/newReport")
    public Response newReport(){


        return Response.succ("");
    }

}
