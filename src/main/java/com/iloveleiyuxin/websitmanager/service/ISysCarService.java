package com.iloveleiyuxin.websitmanager.service;

import com.iloveleiyuxin.websitmanager.entity.SysCar;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
