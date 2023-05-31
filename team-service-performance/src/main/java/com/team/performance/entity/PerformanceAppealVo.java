package com.team.performance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 绩效评价申诉表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_performance_appeal")
public class PerformanceAppealVo implements Serializable {

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
     * 删除标识;0：正常，1已删除
     */
    @TableField("IS_DELETED")
    private Integer isDeleted;

    /**
     * 申诉考核周期;1：月，2：季度
     */
    @TableField("ASSESSMENT_CYCLE")
    private Integer assessmentCycle;

    /**
     * 申诉考核时间
     */
    @TableField("ASSESSMENT_TIME")
    private Date assessmentTime;

    /**
     * 申诉原因
     */
    @TableField("APPEAL_REASON")
    private String appealReason;

    /**
     * 申诉人
     */
    @TableField("APPEAL_PEOPLE")
    private String appealPeople;

    /**
     * 申诉时间
     */
    @TableField("APPEAL_TIME")
    private Date appealTime;

    /**
     * 申诉备注
     */
    @TableField("APPEAL_BREAK")
    private String appealBreak;

    /**
     * 反馈状态：同意/驳回;1: 同意;2: 驳回
     */
    @TableField("FEEDBACK_STATUS")
    private Integer feedbackStatus;

    /**
     * 反馈原因
     */
    @TableField("FEEDBACK_REASON")
    private String feedbackReason;

    /**
     * 反馈时间
     */
    @TableField("FEEDBACK_TIME")
    private Date feedbackTime;


}
