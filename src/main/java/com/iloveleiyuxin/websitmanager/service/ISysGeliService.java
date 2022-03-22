package com.iloveleiyuxin.websitmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.entity.SysGeli;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iloveleiyuxin.websitmanager.vo.GeLiVo;

import java.util.List;

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

    List<GeLiVo> selectVo(QueryWrapper<SysGeli> queryWrapper);
}
