package com.iloveleiyuxin.websitmanager.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeLiVo {
    @TableField("geliUser")
    private Integer geliuser;

    @TableField("userName")
    private String username;

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
    private String lastplace;
}
