package com.iloveleiyuxin.websitmanager.service.impl;

import com.iloveleiyuxin.websitmanager.entity.CliUser;
import com.iloveleiyuxin.websitmanager.entity.SysHealthreport;
import com.iloveleiyuxin.websitmanager.mapper.CliUserMapper;
import com.iloveleiyuxin.websitmanager.mapper.SysHealthreportMapper;
import com.iloveleiyuxin.websitmanager.service.ISysHealthreportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-02-20
 */
@Slf4j
@Service
public class SysHealthreportServiceImpl extends ServiceImpl<SysHealthreportMapper, SysHealthreport> implements ISysHealthreportService {

    @Autowired
    SysHealthreportMapper sysHealthreportMapper;
    @Autowired
    CliUserMapper cliUserMapper;
    @Override
    @Transactional
    public boolean newReport(SysHealthreport sysHealthreport) {
        log.info("开始事务");
        CliUser user = cliUserMapper.selectById(sysHealthreport.getHealthuser());
        Assert.notNull(user,"查无此人:用户ID：["+sysHealthreport.getId()+"]");
        user.setHealthstate(99);
        cliUserMapper.updateById(user);
        if(!sysHealthreport.getRnatest().equals("阴性")&&!sysHealthreport.getRnatest().equals("阳性")&&!sysHealthreport.getRnatest().equals("未检测")){
            if(sysHealthreport.getRnatest()== null){
                sysHealthreport.setRnatest("未检测");
            }
            throw new IllegalArgumentException("核酸结果仅能为阴性或者阳性，或者填入未检测");
        }

        sysHealthreportMapper.insert(sysHealthreport);
        log.info("结束事务");
        return true;
    }
}
