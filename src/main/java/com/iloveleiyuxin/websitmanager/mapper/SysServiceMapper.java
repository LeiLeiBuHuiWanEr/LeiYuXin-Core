package com.iloveleiyuxin.websitmanager.mapper;

import com.iloveleiyuxin.websitmanager.entity.SysService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iloveleiyuxin.websitmanager.vo.ServiceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-04-09
 */
@Mapper
public interface SysServiceMapper extends BaseMapper<SysService> {
    List<ServiceVo> mapSelectVo(@Param("filterMap")Map<String,String> filterMap);

}
