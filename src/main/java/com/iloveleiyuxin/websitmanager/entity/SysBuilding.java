package com.iloveleiyuxin.websitmanager.entity;

import com.iloveleiyuxin.websitmanager.entity.BaseEntity;
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
    public SysBuilding(Integer id, String name, Integer units, Integer peoplecounts) {
        this.id = id;
        this.name = name;
        this.units = units;
        this.peoplecounts = peoplecounts;
    }

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer units;

    @TableField("peopleCounts")
    private Integer peoplecounts;


}
