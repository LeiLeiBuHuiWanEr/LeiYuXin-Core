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
 * @since 2022-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysActivity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 活动主办方
     */
    @TableField("activityUser")
    private Integer activityuser;

    /**
     * 活动地点
     */
    @TableField("activityPlace")
    private String activityplace;

    /**
     * 活动名称
     */
    @TableField("activityName")
    private String activityname;

    /**
     * 预计参与人数
     */
    @TableField("peopleCounts")
    private Integer peoplecounts;

    /**
     * 开始时间
     */
    @TableField("startTime")
    private LocalDateTime starttime;

    /**
     * 结束时间
     */
    @TableField("endTime")
    private LocalDateTime endtime;

    /**
     * 活动类型：1会议 2志愿活动 3展览 4婚丧庆典 5其他
     */
    @TableField("activityType")
    private Integer activitytype;

    /**
     * 状态：0未审核 1审批通过 2审批不通过
     */
    private Integer state;

    /**
     * 审核人
     */
    private Integer auditor;

    /**
     * 审核意见
     */
    @TableField("auditOpinion")
    private String auditopinion;


}
