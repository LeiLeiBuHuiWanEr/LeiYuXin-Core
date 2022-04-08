package com.iloveleiyuxin.websitmanager.service;

import com.iloveleiyuxin.websitmanager.entity.CliUser;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-28
 */
public interface ICliUserService extends IService<CliUser> {

    /**
     * 添加一个用户,由于里面有事务，需要调用sysUnit，不能简单的使用BaseService
     */
    boolean addCliUser(CliUser user);

    /**
     * 修改一个用户
     * @return
     */
    boolean updateCliUser(CliUser user);


    List<Map<String,Object>> healthAnalysis();

    List<Map<String,Object>> quarantineAnalysis();

    List<Map<String, Object>> permanentAnalysis();

    Map<String,Object> registerAnalysis();
}
