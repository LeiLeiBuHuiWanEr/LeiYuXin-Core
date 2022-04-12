package com.iloveleiyuxin.websitmanager.mapper;

import com.iloveleiyuxin.websitmanager.entity.SysHouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
public interface SysHouseMapper extends BaseMapper<SysHouse> {
    List<SysHouse> mapSelect(@Param("filterMap") Map<String,String> filterMap);

}
