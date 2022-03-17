package com.iloveleiyuxin.websitmanager.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
@EqualsAndHashCode(callSuper = true)
public class SysGeli extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("geliUser")
    private Integer geliuser;

    @TableField("geliDescription")
    private String gelidescription;

    /**
     * 隔离类型，1为居家隔离，2为集中隔离，3为定点医院隔离
     */
    @TableField("geliType")
    private Integer gelitype;

    @TableField("geliReason")
    private String gelireason;

    /**
     * 隔离时长
     */
    @TableField("geliDateCount")
    private Integer gelidatecount;

    @TableField("beginDate")
    private LocalDateTime begindate;

    @TableField("endDate")
    private LocalDateTime enddate;

    @TableField("rnaTest")
    private String rnatest;

    /**
     * 返回位置
     */
    @TableField("lastPlace")
    private Integer lastplace;


}
