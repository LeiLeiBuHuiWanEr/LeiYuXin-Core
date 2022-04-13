package com.iloveleiyuxin.websitmanager.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.iloveleiyuxin.websitmanager.entity.SysHealthreport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iloveleiyuxin.websitmanager.vo.HealthReportVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-02-20
 */
@Mapper
public interface SysHealthreportMapper extends BaseMapper<SysHealthreport> {
    List<HealthReportVo> selectVo(@Param(Constants.WRAPPER) QueryWrapper<SysHealthreport> wrapper);

    List<HealthReportVo> mapSelect(@Param("filterMap") Map<String,String> filterMap);
}
