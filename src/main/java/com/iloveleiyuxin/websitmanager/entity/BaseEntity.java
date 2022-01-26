package com.iloveleiyuxin.websitmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BaseEntity {
    @TableId(type = IdType.INPUT)
    protected Integer id;
}
