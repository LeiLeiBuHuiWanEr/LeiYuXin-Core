package com.iloveleiyuxin.websitmanager.security;

import com.iloveleiyuxin.websitmanager.entity.SysUser;
import com.iloveleiyuxin.websitmanager.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    ISysUserService sysUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.getByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        AccountUser acu = new AccountUser(user.getId(),user.getUsername(),user.getUserpassword(),getUserAuthority(user.getId()));
        return acu;
    }

    /**
     * 获取用户权限信息（角色权限）
     * @param userId
     * @return
     */
    public List<GrantedAuthority> getUserAuthority(Integer userId){
        // 角色(ROLE_admin)、菜单操作权限 sys:user:list
        String authority = sysUserService.getUserAuthorityInfo(userId);  // ROLE_admin,ROLE_normal,sys:user:list,....

        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
