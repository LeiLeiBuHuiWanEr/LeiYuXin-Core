package com.iloveleiyuxin.websitmanager.service;

import com.iloveleiyuxin.websitmanager.entity.SysService;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iloveleiyuxin.websitmanager.vo.ServiceVo;

import java.util.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-04-09
 */
public interface ISysServiceService extends IService<SysService> {
    List<ServiceVo> mapSelectVo(Map<String,String> filterMap);

    SysService change(SysService sysService);

}
