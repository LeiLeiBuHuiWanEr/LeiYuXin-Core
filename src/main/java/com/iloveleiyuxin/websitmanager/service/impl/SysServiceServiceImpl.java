package com.iloveleiyuxin.websitmanager.service.impl;

import com.iloveleiyuxin.websitmanager.entity.SysService;
import com.iloveleiyuxin.websitmanager.mapper.SysServiceMapper;
import com.iloveleiyuxin.websitmanager.service.ISysServiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iloveleiyuxin.websitmanager.vo.ServiceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-04-09
 */
@Service
public class SysServiceServiceImpl extends ServiceImpl<SysServiceMapper, SysService> implements ISysServiceService {

    @Autowired
    SysServiceMapper sysServiceMapper;
    @Override
    public List<ServiceVo> mapSelectVo(Map<String, String> filterMap) {
        return sysServiceMapper.mapSelectVo(filterMap);
    }
}
