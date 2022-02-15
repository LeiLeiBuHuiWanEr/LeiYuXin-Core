package com.iloveleiyuxin.websitmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.entity.SysCar;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iloveleiyuxin.websitmanager.vo.CarVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-02-03
 */
public interface ISysCarService extends IService<SysCar> {
    /**
     * 添加一辆车，需要有事务操作
     */
    boolean insertCar(SysCar car);

    /**
     * 修改车辆信息，需要有事务操作
     */
    boolean changeCar(SysCar sysCar);
    /**
     * Vo查询一个
     */
    CarVo selectOneVo(String number);

    List<CarVo> selectList(QueryWrapper queryWrapper);

}
