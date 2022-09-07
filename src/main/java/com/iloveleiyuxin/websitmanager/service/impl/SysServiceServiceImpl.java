package com.iloveleiyuxin.websitmanager.service.impl;

import com.iloveleiyuxin.websitmanager.entity.CliUser;
import com.iloveleiyuxin.websitmanager.entity.SysService;
import com.iloveleiyuxin.websitmanager.mapper.CliUserMapper;
import com.iloveleiyuxin.websitmanager.mapper.SysServiceMapper;
import com.iloveleiyuxin.websitmanager.service.ISysServiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iloveleiyuxin.websitmanager.vo.ServiceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-04-09
 */
@Service
@Slf4j
public class SysServiceServiceImpl extends ServiceImpl<SysServiceMapper, SysService> implements ISysServiceService {

    @Autowired
    SysServiceMapper sysServiceMapper;
    @Autowired
    CliUserMapper cliUserMapper;

    @Override
    public List<ServiceVo> mapSelectVo(Map<String, String> filterMap) {
        return sysServiceMapper.mapSelectVo(filterMap);
    }

    @Override
    @Transactional
    public SysService change(SysService sysService) {
        log.info("开始事务");
        //检查申请人是否存在
        if(sysService.getServiceuser()!=null){
            CliUser u = cliUserMapper.selectById(sysService.getServiceuser());
            if(u == null){
                throw new NullPointerException("申请人不存在！");
            }
        }
        sysServiceMapper.updateById(sysService);
        log.info("结束事务");
        return sysService;
    }
}
