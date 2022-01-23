package com.iloveleiyuxin.websitmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.entity.SysRole;
import com.iloveleiyuxin.websitmanager.entity.SysUser;
import com.iloveleiyuxin.websitmanager.mapper.SysUserMapper;
import com.iloveleiyuxin.websitmanager.service.ISysRoleService;
import com.iloveleiyuxin.websitmanager.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iloveleiyuxin.websitmanager.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-02
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    RedisUtils redisUtil;

    @Autowired
    ISysRoleService sysRoleService;

    @Override
    public SysUser getByUsername(String username) {
        return getOne(new QueryWrapper<SysUser>().eq("userName", username));
    }
    @Override
    public SysUser getByUserId(int id){
        return sysUserMapper.selectById(id);
    }
    @Override
    public String getUserAuthorityInfo(Integer userId) {

        SysUser sysUser = sysUserMapper.selectById(userId);

        //  ROLE_admin,ROLE_normal,sys:user:list,....
        String authority = "";
        if(sysUser.getUserstate()){
            if (redisUtil.hasKey("GrantedAuthority:" + sysUser.getUsername())) {
                authority = (String) redisUtil.get("GrantedAuthority:" + sysUser.getUsername());
            } else {
                // 获取角色编码
                List<SysRole> roles = sysRoleService.getRoleByUserId(userId);

                if (roles.size() > 0) {
                    String roleCodes = roles.stream().map(r -> "ROLE_" + r.getId()).collect(Collectors.joining(","));
                    authority = roleCodes.concat(",");
                }
                System.out.println(authority);

                redisUtil.set("GrantedAuthority:" + sysUser.getUsername(), authority, 60 * 60);
            }
        }
        else {
            authority = "ROLE_INVALID";
            log.warn("用户未经过验证，需要管理员进行验证");
        }
        return authority;
    }


    public List<SysUser> getUserList(){
        Map<String,Object> queryWrapper = new HashMap<>();
        queryWrapper.put("userRole",2);
        List<SysUser> resultList = sysUserMapper.selectByMap(queryWrapper);
        return resultList;
    }
}
