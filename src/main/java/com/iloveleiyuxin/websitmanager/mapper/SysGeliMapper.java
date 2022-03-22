package com.iloveleiyuxin.websitmanager.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.iloveleiyuxin.websitmanager.entity.SysGeli;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iloveleiyuxin.websitmanager.vo.GeLiVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-03-16
 */
@Repository
public interface SysGeliMapper extends BaseMapper<SysGeli> {
    List<GeLiVo> selectVo(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
