package com.iloveleiyuxin.websitmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.entity.SysRole;
import com.iloveleiyuxin.websitmanager.mapper.SysRoleMapper;
import com.iloveleiyuxin.websitmanager.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-09
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<SysRole> getRoleByUserId(Integer id) {
        return this.list(new QueryWrapper<SysRole>().inSql("id","select userRole from sys_user where id =" + id));
    }
}
