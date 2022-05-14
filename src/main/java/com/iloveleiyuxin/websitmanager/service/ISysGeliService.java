package com.iloveleiyuxin.websitmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.entity.SysGeli;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iloveleiyuxin.websitmanager.vo.GeLiVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-03-16
 */
public interface ISysGeliService extends IService<SysGeli> {
    boolean isGeli(Integer id);

    boolean save(SysGeli sysGeli);

    List<GeLiVo> selectVo(QueryWrapper<SysGeli> queryWrapper);

    List<GeLiVo> mapSelectVo(Map<String,String> filterMap);
}
