package com.iloveleiyuxin.websitmanager.service.impl;

import com.iloveleiyuxin.websitmanager.entity.SysCar;
import com.iloveleiyuxin.websitmanager.mapper.CliUserMapper;
import com.iloveleiyuxin.websitmanager.mapper.SysCarMapper;
import com.iloveleiyuxin.websitmanager.service.ISysCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-02-03
 */
@Slf4j
@Service
public class SysCarServiceImpl extends ServiceImpl<SysCarMapper, SysCar> implements ISysCarService {

    @Autowired
    SysCarMapper sysCarMapper;
    @Autowired
    CliUserMapper cliUserMapper;

    @Override
    @Transactional
    public boolean insertCar(SysCar car) {
        log.info("开始事务");
        Integer userNo = car.getCarowner();
        if(cliUserMapper.selectById(userNo) == null){
            throw new NullPointerException("没有业主信息！");
        }else{
            sysCarMapper.insert(car);
            log.info("结束事务");
        }
        return true;
    }


}


