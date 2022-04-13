package com.iloveleiyuxin.websitmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.entity.SysHealthreport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iloveleiyuxin.websitmanager.vo.HealthReportVo;

import java.util.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-02-20
 */
public interface ISysHealthreportService extends IService<SysHealthreport> {

    boolean newReport(SysHealthreport sysHealthreport);

    List<HealthReportVo> selectVo(QueryWrapper<SysHealthreport> queryWrapper);

    List<HealthReportVo> mapSelect(Map<String,String> filterMap);

}
