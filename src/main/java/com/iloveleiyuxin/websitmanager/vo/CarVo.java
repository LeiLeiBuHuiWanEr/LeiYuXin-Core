package com.iloveleiyuxin.websitmanager.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 车牌号码
     */
    @TableField("carNo")
    private String carno;

    /**
     * 车主姓名
     */
    @TableField("carOwner")
    private String carowner;

    /**
     * 车主编号
     */
    @TableField("ownerNo")
    private String ownerno;

    /**
     * 车辆颜色
     */
    @TableField("carColor")
    private String carcolor;

    /**
     * 车辆品牌
     */
    @TableField("carBrand")
    private String carbrand;

    /**
     * 车辆状态：1为正常，0为禁止入场，2为欠费
     */
    @TableField("carState")
    private Integer carstate;

    /**
     * 车辆管理费用基数
     */
    private BigDecimal fee;
}
