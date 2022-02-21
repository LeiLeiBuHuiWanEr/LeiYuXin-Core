package com.iloveleiyuxin.websitmanager.service;

import com.iloveleiyuxin.websitmanager.entity.SysHealthreport;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
