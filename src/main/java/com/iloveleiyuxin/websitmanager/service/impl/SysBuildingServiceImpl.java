package com.iloveleiyuxin.websitmanager.service.impl;

import com.iloveleiyuxin.websitmanager.common.exception.LeiYuXinFallenInLoveException;
import com.iloveleiyuxin.websitmanager.entity.SysBuilding;
import com.iloveleiyuxin.websitmanager.entity.SysUnit;
import com.iloveleiyuxin.websitmanager.mapper.SysBuildingMapper;
import com.iloveleiyuxin.websitmanager.mapper.SysUnitMapper;
import com.iloveleiyuxin.websitmanager.service.ISysBuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-23
 */
@Slf4j
@Service
public class SysBuildingServiceImpl extends ServiceImpl<SysBuildingMapper, SysBuilding> implements ISysBuildingService {

    @Autowired
    private SysBuildingMapper sysBuildingMapper;

    @Autowired
    private SysUnitMapper sysUnitMapper;

    @Override
    @Transactional
    public boolean addBuilding(Integer buildingNo, Integer units) {
        log.info("开始事务");
        SysBuilding sysBuilding = new SysBuilding(buildingNo,buildingNo+"楼",units,0);
        sysBuildingMapper.insert(sysBuilding);
        for (int i = 0; i < units; i++) {
            sysUnitMapper.insert(new SysUnit(buildingNo*100+i+1,buildingNo+"楼"+(i+1)+"门",buildingNo,0));
        }
        log.info("结束事务");
        return true;
    }

    @Override
    public List<Map<String,Object>> buildingCapacityInfo() {
        return sysBuildingMapper.selectBuildingCapacityInfo();
    }
}
