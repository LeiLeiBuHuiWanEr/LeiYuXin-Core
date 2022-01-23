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
public class SysBuilding extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer units;

    @TableField("peopleCounts")
    private Integer peoplecounts;


}
