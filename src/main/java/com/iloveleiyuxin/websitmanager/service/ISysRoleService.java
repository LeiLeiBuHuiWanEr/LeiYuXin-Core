package com.iloveleiyuxin.websitmanager.service;

import com.iloveleiyuxin.websitmanager.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-09
 */
public interface ISysRoleService extends IService<SysRole> {
    List<SysRole> getRoleByUserId(Integer id);
}
