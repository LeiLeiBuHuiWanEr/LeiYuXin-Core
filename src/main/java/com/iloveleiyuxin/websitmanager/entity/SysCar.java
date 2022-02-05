package com.iloveleiyuxin.websitmanager.entity;

import java.math.BigDecimal;
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
 * @since 2022-02-03
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysCar extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号码
     */
    @TableField("carNo")
    private String carno;

    /**
     * 车主编号
     */
    @TableField("carOwner")
    private Integer carowner;

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


    public SysCar(Integer id,String carno, Integer carowner, String carcolor, String carbrand, Integer carstate, BigDecimal fee) {
        this.setId(id);
        this.carno = carno;
        this.carowner = carowner;
        this.carcolor = carcolor;
        this.carbrand = carbrand;
        this.carstate = carstate;
        this.fee = fee;
    }

}
