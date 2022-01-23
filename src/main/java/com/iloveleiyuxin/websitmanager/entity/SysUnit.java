package com.iloveleiyuxin.websitmanager.entity;

import com.iloveleiyuxin.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUnit extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String description;

    private Integer building;

    @TableField("houseCounts")
    private Integer housecounts;


}
