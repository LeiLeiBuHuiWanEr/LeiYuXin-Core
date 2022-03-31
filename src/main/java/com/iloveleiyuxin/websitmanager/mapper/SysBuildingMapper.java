package com.iloveleiyuxin.websitmanager.mapper;

import com.iloveleiyuxin.websitmanager.entity.SysBuilding;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-23
 */
@Repository
public interface SysBuildingMapper extends BaseMapper<SysBuilding> {
    int updatePeopleCount(Integer id,Integer countNum);

}
