package com.iloveleiyuxin.websitmanager.service.impl;

import com.iloveleiyuxin.websitmanager.entity.SysBuilding;
import com.iloveleiyuxin.websitmanager.mapper.SysBuildingMapper;
import com.iloveleiyuxin.websitmanager.mapper.SysUnitMapper;
import com.iloveleiyuxin.websitmanager.service.ISysBuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-23
 */
@Service
public class SysBuildingServiceImpl extends ServiceImpl<SysBuildingMapper, SysBuilding> implements ISysBuildingService {

    @Autowired
    private SysBuildingMapper sysBuildingMapper;

    @Autowired
    private SysUnitMapper sysUnitMapper;

    @Override
    @Transactional
    public void addBuilding(Integer buildingNo, Integer units) {

    }
}
