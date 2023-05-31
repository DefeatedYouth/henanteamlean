package com.team.performance.dto.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 新增专责绩效对象信息
 * @author zhangm
 * @date 2022/7/27$
 */
@Data
public class AddPerformanceModelDto {

    /**
     * 指标名称
     */
    private String quateName;

    /**
     * 细则
     */
    private String detailRule;

    /**
     * 考核周期;1：月，2：季度
     */
    private Integer assessmentCycle;

    /**
     * 考核人;(角色编码, name = "id")
     */
    private String assessor;

    /**
     * 考核对象;(角色编码, name = "id")
     */
    private String assessmentObject;

    /**
     * 积分
     */
    private BigDecimal integral;

    /**
     * 生产者id
     */
    private String productorId;

    /**
     * 创建人
     */
    private String createdBy;

}
