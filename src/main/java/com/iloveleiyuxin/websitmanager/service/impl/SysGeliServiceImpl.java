package com.iloveleiyuxin.websitmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iloveleiyuxin.websitmanager.entity.SysGeli;
import com.iloveleiyuxin.websitmanager.mapper.SysGeliMapper;
import com.iloveleiyuxin.websitmanager.service.ISysGeliService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-03-16
 */
@Service
@Slf4j
public class SysGeliServiceImpl extends ServiceImpl<SysGeliMapper, SysGeli> implements ISysGeliService {

    @Autowired
    SysGeliMapper sysGeliMapper;

    @Override
    @Transactional
    public boolean isGeli(Integer id) {
        log.info("开始事务");
        Boolean state = false;
        //获取现在的时间
        Calendar now = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String todayStr = format.format(now.getTime());
        //包装QueryWrapper
        QueryWrapper<SysGeli> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysGeli::getGeliuser,id)
                .ge(SysGeli::getEnddate,todayStr);

        List<SysGeli> list = sysGeliMapper.selectList(queryWrapper);
        if(list.size() > 0){
            state = true;
        }
        log.info("结束事务");
        return state;
    }
}
