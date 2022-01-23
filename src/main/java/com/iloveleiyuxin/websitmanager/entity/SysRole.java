package com.iloveleiyuxin.websitmanager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-01-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("roleName")
    private String rolename;

    @TableField("roleRemark")
    private String roleremark;


}
