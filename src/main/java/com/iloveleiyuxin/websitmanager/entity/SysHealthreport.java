package com.iloveleiyuxin.websitmanager.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.iloveleiyuxin.websitmanager.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class SysHealthreport extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("healthUser")
    private Integer healthuser;

    @TableField("healthDescription")
    private String healthdescription;

    @TableField("reportDate")
    private LocalDateTime reportdate;

    @TableField("bodyTemperature")
    private Double bodytemperature;

    /**
     * 核酸检测结果
     */
    @TableField("rnaTest")
    private String rnatest;
    /**
     * 是否需要隔离?
     * 0不需要，其余代表需要隔离天数
     */
    @TableField("needQuarantine")
    private Integer needquarantine;


    public SysHealthreport(Integer id,Integer healthuser, String healthdescription, LocalDateTime reportdate, Double bodytemperature, String rnatest, Integer needquarantine) {
        this.setId(id);
        this.healthuser = healthuser;
        this.healthdescription = healthdescription;
        this.reportdate = reportdate;
        this.bodytemperature = bodytemperature;
        this.rnatest = rnatest;
        this.needquarantine = needquarantine;
    }
}
