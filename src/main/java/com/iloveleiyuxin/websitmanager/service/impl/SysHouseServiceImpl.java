package com.iloveleiyuxin.websitmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.common.exception.LeiYuXinFallenInLoveException;
import com.iloveleiyuxin.websitmanager.entity.SysHouse;
import com.iloveleiyuxin.websitmanager.entity.SysUnit;
import com.iloveleiyuxin.websitmanager.mapper.SysBuildingMapper;
import com.iloveleiyuxin.websitmanager.mapper.SysHouseMapper;
import com.iloveleiyuxin.websitmanager.mapper.SysUnitMapper;
import com.iloveleiyuxin.websitmanager.service.ISysHouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
public class SysHouseServiceImpl extends ServiceImpl<SysHouseMapper, SysHouse> implements ISysHouseService {
    @Autowired
    SysHouseMapper sysHouseMapper;
    @Autowired
    SysBuildingMapper sysBuildingMapper;
    @Autowired
    SysUnitMapper sysUnitMapper;

    @Transactional
    @Override
    public boolean addHouse(String buildingNo, String unitNo, String houseNo, Double area, Double fee) {
        log.info("开始事务");
        Integer fullHouseNo;
        Integer fullUnitNo;
        if(!(unitNo.length() == 1)){
            fullHouseNo = Integer.valueOf(buildingNo+unitNo+houseNo);
            fullUnitNo = Integer.valueOf(buildingNo+unitNo);
        }
        else {
            fullHouseNo = Integer.valueOf(buildingNo+"0"+unitNo+houseNo);
            fullUnitNo = Integer.valueOf(buildingNo+"0"+unitNo);
        }

        QueryWrapper<SysUnit> wrapper = new QueryWrapper<>();
        wrapper.eq("id",fullUnitNo);

        SysUnit selectedUnit = sysUnitMapper.selectOne(wrapper);
        if(selectedUnit == null){
            throw new LeiYuXinFallenInLoveException("没有查到单元信息！");
        }
        log.info("查到单元信息"+selectedUnit+"，正在准备更新单元信息!");
        selectedUnit.setHousecounts(selectedUnit.getHousecounts() + 1);
        sysUnitMapper.update(selectedUnit,wrapper);
        log.info("单元信息更新完毕！");

        sysHouseMapper.insert(new SysHouse(
                fullHouseNo,Integer.valueOf(buildingNo),fullUnitNo,buildingNo+"楼"+unitNo+"单元"+houseNo+"号",0,new BigDecimal(area),new BigDecimal(fee)
        ));

        log.info("结束事务");
        return true;
    }
}
