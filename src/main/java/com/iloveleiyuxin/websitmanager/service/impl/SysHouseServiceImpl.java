package com.iloveleiyuxin.websitmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.common.exception.LeiYuXinFallenInLoveException;
import com.iloveleiyuxin.websitmanager.entity.CliUser;
import com.iloveleiyuxin.websitmanager.entity.SysHouse;
import com.iloveleiyuxin.websitmanager.entity.SysUnit;
import com.iloveleiyuxin.websitmanager.mapper.CliUserMapper;
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
import java.util.List;

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
    @Autowired
    CliUserMapper cliUserMapper;

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
            throw new NullPointerException("没有查到单元信息！");
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

    @Transactional
    @Override
    public boolean removeHouse(String id) {
        log.info("开始事务");
        SysHouse preDel = sysHouseMapper.selectById(Integer.valueOf(id));
        List<CliUser> userList = cliUserMapper.selectList(new QueryWrapper<CliUser>().eq("locate",Integer.valueOf(id)));
        if (preDel == null){
            throw new NullPointerException("没有当前信息，删除个锤子啊！");
        }
        if(preDel.getState() != 0 || (userList != null && userList.size() != 0)){
            throw new LeiYuXinFallenInLoveException("无法删除，因为当前状态为有人居住状态！");
        }
        log.info("准备删除");
        sysHouseMapper.deleteById(Integer.valueOf(id));
        QueryWrapper<SysUnit> wrapper = new QueryWrapper<>();
        wrapper.eq("id",preDel.getUnit());
        SysUnit selectedUnit = sysUnitMapper.selectOne(wrapper);
        selectedUnit.setHousecounts(selectedUnit.getHousecounts() - 1);
        sysUnitMapper.update(selectedUnit,wrapper);
        log.info("结束事务");

        return false;
    }

    @Transactional
    @Override
    public boolean updateHouse(String id,Double fee,Double area,String description) {
        SysHouse preUpdate = sysHouseMapper.selectById(id);
        if(preUpdate == null){
            throw new NullPointerException("没有当前信息，修改个锤子啊？");
        }
        if(fee != null) preUpdate.setServicefeebasic(BigDecimal.valueOf(fee));
        if(area != null) preUpdate.setArea(BigDecimal.valueOf(area));
        if(description != null) preUpdate.setName(description);

        sysHouseMapper.update(preUpdate,new QueryWrapper<SysHouse>().eq("id",id));
        return true;
    }
}
