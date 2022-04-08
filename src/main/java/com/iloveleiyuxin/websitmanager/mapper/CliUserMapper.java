package com.iloveleiyuxin.websitmanager.mapper;

import com.iloveleiyuxin.websitmanager.entity.CliUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-28
 */
@Repository
public interface CliUserMapper extends BaseMapper<CliUser> {
    List<Map<String,Object>> healthAnalysis();

    List<Map<String,Object>> quarantineAnalysis();

    List<Map<String,Object>> permanentAnalysis();

    List<Map<String,Object>> registerAnalysis();

}
