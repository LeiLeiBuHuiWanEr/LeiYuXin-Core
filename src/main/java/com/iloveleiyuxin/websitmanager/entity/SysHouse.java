package com.iloveleiyuxin.websitmanager.entity;

import java.math.BigDecimal;
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
public class SysHouse extends BaseInsertEntity {

    public SysHouse(Integer id,Integer building, Integer unit, String name, Integer state, BigDecimal area, BigDecimal servicefeebasic,String layout) {
        this.setId(id);
        this.building = building;
        this.unit = unit;
        this.name = name;
        this.state = state;
        this.area = area;
        this.servicefeebasic = servicefeebasic;
        this.layout = layout;
    }

    private static final long serialVersionUID = 1L;

    private Integer building;

    private Integer unit;

    private String name;

    /**
     * 0为空闲，1为正常
     */
    private Integer state;

    private BigDecimal area;

    @TableField("serviceFeeBasic")
    private BigDecimal servicefeebasic;

    private String layout;


}
