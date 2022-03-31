package com.iloveleiyuxin.websitmanager.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.iloveleiyuxin.websitmanager.entity.SysCar;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iloveleiyuxin.websitmanager.vo.CarVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-02-03
 */
@Mapper
public interface SysCarMapper extends BaseMapper<SysCar> {
    /**
     * 根据车牌号查一个
     * @param name
     * @return
     */
    CarVo selectOneVo(String name);

    /**
     * 根据姓名查一组
     */
    List<CarVo> selectListVo(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    /**
     * Map传参查一组
     */
    List<CarVo> selectMapVo(@Param("filterMap") Map<String,String> filterMap);
}
