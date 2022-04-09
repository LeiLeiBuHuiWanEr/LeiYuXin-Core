package com.iloveleiyuxin.websitmanager.service;

import com.iloveleiyuxin.websitmanager.entity.SysBuilding;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-23
 */
public interface ISysBuildingService extends IService<SysBuilding> {
    boolean addBuilding(Integer buildingNo,Integer units);


    List<Map<String,Object>> buildingCapacityInfo();

}
