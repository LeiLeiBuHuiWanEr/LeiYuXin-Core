package com.iloveleiyuxin.websitmanager.mapper;

import com.iloveleiyuxin.websitmanager.entity.SysBuilding;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.*;
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

    @Select("SELECT id AS name,peopleCounts AS value FROM sys_building")
    List<Map<String,Object>> selectBuildingCapacityInfo();

}
