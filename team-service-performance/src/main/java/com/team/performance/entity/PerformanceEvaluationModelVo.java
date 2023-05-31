package com.team.performance.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 绩效评价模板表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_performance_evaluation_model")
public class PerformanceEvaluationModelVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 更新人
     */
    @TableField("UPDATED_BY")
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField("UPDATED_TIME")
    private Date updatedTime;

    /**
     * 创建人
     */
    @TableField("CREATED_BY")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("CREATED_TIME")
    private Date createdTime;

    /**
     * 是否删除;0：正常，1：已删除
     */
    @TableField("IS_DELETED")
    private Integer isDeleted;

    /**
     * 编制单位ID
     */
    @TableField("COMPANY_ID")
    private String companyId;

    /**
     * 编制单位
     */
    @TableField("COMPANY_NAME")
    private String companyName;

    /**
     * 是否含有班组：1不包含，2包含（见表：T_PERFORMANCE_MODEL_TEAMREL）
     */
    @TableField("IS_HAV_TEAM")
    private Integer isHavTeam;

    /**
     * 指标名称
     */
    @TableField("QUATE_NAME")
    private String quateName;

    /**
     * 细则
     */
    @TableField("DETAIL_RULE")
    private String detailRule;

    /**
     * 考核周期;1：月，2：季度
     */
    @TableField("ASSESSMENT_CYCLE")
    private Integer assessmentCycle;

    /**
     * 考核人;(角色编码)
     */
    @TableField("ASSESSOR")
    private String assessor;

    /**
     * 考核对象;(角色编码)
     */
    @TableField("ASSESSMENT_OBJECT")
    private String assessmentObject;

    /**
     * 积分
     */
    @TableField("INTEGRAL")
    private BigDecimal integral;

    /**
     * 发布时间
     */
    @TableField("PUBLISH_TIME")
    private Date publishTime;

    /**
     * 发布状态：1未发布，2发布
     */
    @TableField("PUBLISH_STATE")
    private Integer publishState;

    /**
     * 绩效方案来源;1：工区专责，2：班长
     */
    @TableField("SOURCE")
    private Integer source;

    /*非表字段*/
    /**
     * 班组名称
     */
    @TableField("teamName")
    private String teamName;

    /**
     * 生成者id
     */
    @TableField("PRODUCTOR_ID")
    private String productorId;
}
