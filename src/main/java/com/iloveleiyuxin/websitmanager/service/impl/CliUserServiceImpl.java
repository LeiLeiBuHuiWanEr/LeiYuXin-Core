package com.iloveleiyuxin.websitmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.entity.CliUser;
import com.iloveleiyuxin.websitmanager.entity.SysBuilding;
import com.iloveleiyuxin.websitmanager.entity.SysHouse;
import com.iloveleiyuxin.websitmanager.mapper.CliUserMapper;
import com.iloveleiyuxin.websitmanager.mapper.SysBuildingMapper;
import com.iloveleiyuxin.websitmanager.mapper.SysHouseMapper;
import com.iloveleiyuxin.websitmanager.service.ICliUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-28
 */
@Slf4j
@Service
public class CliUserServiceImpl extends ServiceImpl<CliUserMapper, CliUser> implements ICliUserService {

    @Autowired
    CliUserMapper cliUserMapper;

    @Autowired
    SysBuildingMapper sysBuildingMapper;

    @Autowired
    SysHouseMapper sysHouseMapper;

    @Transactional
    @Override
    public boolean addCliUser(CliUser user) {
        log.info("开始事务");
        cliUserMapper.insert(user);
        Integer locationNo = user.getLocate();
        SysHouse location = sysHouseMapper.selectById(locationNo);
        if (location == null){
            throw new NullPointerException("数据库没有当前房屋信息！");
        }
        if (location.getState() == 0){
            location.setState(1);
        }
        sysHouseMapper.update(location,new QueryWrapper<SysHouse>().eq("id",user.getLocate()));
        log.info("结束事务");
        return true;
    }

    @Override
    @Transactional
    public boolean updateCliUser(CliUser user) {
        log.info("开始事务");
        Integer houseId = user.getLocate();
        SysHouse sysHouse = sysHouseMapper.selectById(houseId);
        if(sysHouse == null){
            throw new NullPointerException("数据库没有当前房屋信息");
        }
        cliUserMapper.updateById(user);
        log.info("结束事务");
        return true;
    }

    @Override
    public List<Map<String, Object>> healthAnalysis() {
        List<Map<String, Object>> list =  cliUserMapper.healthAnalysis();
        for (Map<String,Object> item:list) {
            Integer nameCode = (Integer) item.get("name");
            switch(nameCode){
                case 0: item.put("name","健康");
                    break;
                case 1: item.put("name","有基础疾病");
                    break;
                case 2: item.put("name","有特殊疾病");
                    break;
                case 99: item.put("name","发热、咳嗽");
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> quarantineAnalysis() {
        List<Map<String, Object>> list =  cliUserMapper.quarantineAnalysis();
        for (Map<String,Object> item:list) {
            Integer nameCode = (Integer) item.get("name");
            switch(nameCode){
                case 0: item.put("name","正常");
                    break;
                case 1: item.put("name","集中隔离");
                    break;
                case 2: item.put("name","居家隔离");
                    break;
                case 3: item.put("name","居家健康监测");
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> permanentAnalysis() {
        List<Map<String, Object>> list =  cliUserMapper.permanentAnalysis();
        for (Map<String,Object> item:list) {
            Integer nameCode = (Integer) item.get("name");
            switch(nameCode){
                case 0: item.put("name","非常住");
                    break;
                case 1: item.put("name","常住");
            }
        }
        return list;
    }

    @Override
    public Map<String, Object> registerAnalysis() {
        List<Map<String,Object>> list = cliUserMapper.registerAnalysis();
        Map<String,Object> result = new HashMap<>();
        List<String> axis = new ArrayList();
        List<Integer> value = new ArrayList();
        list.forEach (item->{
            axis.add(item.get("date").toString());
            value.add(Integer.valueOf(item.get("count").toString()));
        });
        result.put("axis",axis);
        result.put("data",value);
        return result;
    }
}
