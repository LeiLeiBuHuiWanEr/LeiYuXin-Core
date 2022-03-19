package com.iloveleiyuxin.websitmanager.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthReportVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    @TableField("healthUser")
    private Integer userid;

    @TableField("userName")
    private String username;

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
     * 0不需要，1需要
     */
    @TableField("needQuarantine")
    private Integer needquarantine;
}
