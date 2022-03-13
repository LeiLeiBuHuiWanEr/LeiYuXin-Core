package com.iloveleiyuxin.websitmanager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.common.Const;
import com.iloveleiyuxin.websitmanager.common.Response;
import com.iloveleiyuxin.websitmanager.entity.SysHealthreport;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    /*查询健康报告数据(按照个人)
        timeflag=1为自定义时间，timeflag不传参默认为查询15天内结果，
        当timeflag = 1时，需要传递一个开始日期
     */
    @GetMapping("info/selectByUserId")
    public Response selectByUserId() throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar now = Calendar.getInstance();
        String startdate;
        String enddate = simpleDateFormat.format(now.getTime());
        String timeflag = req.getParameter("timeflag");
        if(timeflag==null|| timeflag.equals("") || timeflag.equals("0")){
            now.set(Calendar.DAY_OF_MONTH, -15);
            startdate = simpleDateFormat.format(now.getTime());
        }else{
            String startObj = req.getParameter("startdate");
            Date stareD = simpleDateFormat.parse(startObj);
            now.setTime(stareD);
            startdate = simpleDateFormat.format(now.getTime());
        }

        String id = req.getParameter("id");
        if(id==null|| id.equals("")){
            throw new NullPointerException("缺失参数");
        }

        QueryWrapper<SysHealthreport> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysHealthreport::getHealthuser,id)
                .ge(SysHealthreport::getReportdate,startdate).le(SysHealthreport::getReportdate,enddate);


        List<SysHealthreport> list = sysHealthreportService.list
                (queryWrapper);

        return Response.succ(list);
    }

    /*
        查询健康报告（按照时间）
        timeflag=1为自定义开始时间至今，timeflag=2为自定义开始结束时间，timeflag=3为查询今日，不传参默认为查询15天内结果，
        当timeflag = 1时，需要传递一个开始日期
        当timeflag = 2时，需要传递一个开始日期和结束日期
     */
    @GetMapping("info/selectByTime")
    public Response selectByTime() throws Exception{
        String timeflag = req.getParameter("timeflag");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat endDateformat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar now = Calendar.getInstance();
        String startdate;
        String enddate = simpleDateFormat.format(now.getTime());
        if(timeflag == null || timeflag.equals("0")){
            now.set(Calendar.DAY_OF_MONTH, -15);
            startdate = simpleDateFormat.format(now.getTime());
        }else if(timeflag.equals("1")){
            String startObj = req.getParameter("startdate");
            Date stareD = simpleDateFormat.parse(startObj);
            now.setTime(stareD);
            startdate = simpleDateFormat.format(now.getTime());
        }else if (timeflag.equals("2")){
            String startObj = req.getParameter("startdate");
            String endObj = req.getParameter("enddate");
            if(startObj==null || endObj == null){
                throw new NullPointerException("缺失参数");
            }
            Date stareD = simpleDateFormat.parse(startObj);
            Date endD = endDateformat.parse(endObj);
            now.setTime(stareD);
            startdate = simpleDateFormat.format(now.getTime());
        }else {

        }

        return Response.succ("");
    }

}
