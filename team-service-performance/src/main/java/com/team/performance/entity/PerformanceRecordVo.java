package com.team.performance.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 绩效评价记录表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_performance_record")
public class PerformanceRecordVo implements Serializable {

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
     * 被考核班员编码
     */
    @TableField("TEAM_PEO_CODE")
    private String teamPeoCode;

    /**
     * 被考核班员名称
     */
    @TableField("TEAM_PEO_NAME")
    private String teamPeoName;

    /**
     * 最终积分
     */
    @TableField("LAST_INTEGRAL")
    private BigDecimal lastIntegral;

    /**
     * 绩效等级
     */
    @TableField("PERFOR_LEVEL")
    private String perforLevel;

    /**
     * 状态;1:未发布（默认）;2:预发布；3:已发布
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 发布人
     */
    @TableField("PUBLISH_PEOPLE")
    private String publishPeople;

    /**
     * 发布时间
     */
    @TableField("PUBLISH_TIME")
    private Date publishTime;

    /**
     * 考核周期;1：月，2：季度
     */
    @TableField("ASSESSMENT_CYCLE")
    private Integer assessmentCycle;

    /**
     * 绩效模板真实发布时间（即迁移到该表的当前时间）
     */
    @TableField("SHOW_TIME")
    private Date showTime;

    /**
     * 生产者id
     */
    @TableField("PRODUCTOR_ID")
    private String productorId;

    /**
     * 班组编码
     */
    @TableField("TEAM_CODE")
    private String teamCode;

    /**
     * 班员评价详情
     */
    @TableField("detailList")
    private List<PerformanceRecordDetailVo> detailList;
}
