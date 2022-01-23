package com.iloveleiyuxin.websitmanager.service;

import com.iloveleiyuxin.websitmanager.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-02
 */
public interface ISysUserService extends IService<SysUser> {
    SysUser getByUserId(int id);
    SysUser getByUsername(String username);
    String getUserAuthorityInfo(Integer userId);

    List<SysUser> getUserList();

}
