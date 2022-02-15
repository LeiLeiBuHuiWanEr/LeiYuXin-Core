package com.iloveleiyuxin.websitmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.entity.SysCar;
import com.iloveleiyuxin.websitmanager.mapper.CliUserMapper;
import com.iloveleiyuxin.websitmanager.mapper.SysCarMapper;
import com.iloveleiyuxin.websitmanager.service.ISysCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iloveleiyuxin.websitmanager.vo.CarVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

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

    @Override
    @Transactional
    public boolean changeCar(SysCar sysCar) {
        SysCar current = sysCarMapper.selectOne(new QueryWrapper<SysCar>().eq("carNo",sysCar.getCarno()));
        Assert.notNull(current,"没有车辆信息");

        if (sysCar.getCarbrand() != null){
            current.setCarbrand(sysCar.getCarbrand());
        }
        if (sysCar.getCarcolor() != null){
            current.setCarcolor(sysCar.getCarcolor());
        }
        if (sysCar.getCarstate() != null){
            current.setCarstate(sysCar.getCarstate());
        }
        if (sysCar.getFee() != null){
            current.setFee(sysCar.getFee());
        }

        sysCarMapper.updateById(current);

        return true;
    }

    @Override
    public CarVo selectOneVo(String number) {
        return sysCarMapper.selectOneVo(number);
    }

    @Override
    public List<CarVo> selectList(QueryWrapper wrapper) {
        return sysCarMapper.selectListVo(wrapper);
    }


}


