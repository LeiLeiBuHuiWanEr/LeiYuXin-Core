package com.iloveleiyuxin.websitmanager.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ServiceVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键：服务编号
     */
    private Integer id;

    /**
     * 服务提出业主
     */
    private Integer serviceuserid;

    private String serviceuser;

    /**
     * 服务类型
     */
    private String servicetype;

    /**
     * 详情
     */
    private String servicedescription;

    /**
     * 创建服务时间
     */
    private String createdate;

    /**
     * 状态：0为未处理，1为处理中，2为处理完成待评价，3为评价完成
     */
    private String servicestate;

    /**
     * 评分
     */
    private Integer servicescore;

    /**
     * 处理完成日期
     */
    private String enddate;

}
