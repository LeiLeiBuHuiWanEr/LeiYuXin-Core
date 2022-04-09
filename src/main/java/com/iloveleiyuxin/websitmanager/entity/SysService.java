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
 * @since 2022-04-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysService extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 服务提出业主
     */
    @TableField("serviceUser")
    private Integer serviceuser;

    /**
     * 服务类型
     */
    @TableField("serviceType")
    private Integer servicetype;

    /**
     * 详情
     */
    @TableField("serviceDescription")
    private String servicedescription;

    /**
     * 创建服务时间
     */
    @TableField("createDate")
    private LocalDateTime createdate;

    /**
     * 状态：0为未处理，1为处理中，2为处理完成待评价，3为评价完成
     */
    @TableField("serviceState")
    private Integer servicestate;

    /**
     * 评分
     */
    @TableField("serviceScore")
    private Integer servicescore;

    /**
     * 处理完成日期
     */
    @TableField("endDate")
    private LocalDateTime enddate;


}
