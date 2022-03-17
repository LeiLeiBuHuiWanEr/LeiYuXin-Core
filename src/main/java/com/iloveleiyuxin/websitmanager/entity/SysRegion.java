package com.iloveleiyuxin.websitmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.iloveleiyuxin.websitmanager.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-03-16
 */
@Data
public class SysRegion{

    private static final long serialVersionUID = 1L;

    @TableId(value = "region_code", type = IdType.AUTO)
    private Integer regionCode;

    private String regionName;

    private Integer regionLevel;

    private Integer parentRegionCode;


}
