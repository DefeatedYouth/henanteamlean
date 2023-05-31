package com.team.work.entity;

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
 * 工单表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_work_info")
public class WorkInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 工单编码
     */
    @TableField("work_no")
    private String workNo;

    /**
     * 模板编码
     */
    @TableField("model_no")
    private String modelNo;

    /**
     * 工单来源(0手动创建、1运检工单、2模板导入、3上级创建)
     */
    @TableField("work_source")
    private Integer workSource;

    /**
     * 工作内容
     */
    @TableField("work_content")
    private String workContent;

    /**
     * 工作类型(0班组建设、1员工培训、2安全活动、3现场生产、4基建验收、5基础管理、6科技创新、7综合事务、8临时任务、9其他)
     */
    @TableField("work_type")
    private Integer workType;

    /**
     * 工作性质(0出勤、1出差、2培训、3借调)
     */
    @TableField("work_nature")
    private Integer workNature;

    /**
     * 工作等级(0一般任务、1紧急任务、2重要任务)
     */
    @TableField("work_level")
    private Integer workLevel;

    /**
     * 负责人ID
     */
    @TableField("leader_id")
    private String leaderId;

    /**
     * 负责人名称
     */
    @TableField("leader_name")
    private String leaderName;

    /**
     * 标准积分
     */
    @TableField("norm_integral")
    private String normIntegral;

    /**
     * 变电站id
     */
    @TableField("station_id")
    private Integer stationId;

    /**
     * 班组id
     */
    @TableField("team_id")
    private Integer teamId;

    /**
     * 工作状态0新建、1待执行、2执行中、3已完成、4已归档、5已取消
     */
    @TableField("work_status")
    private Integer workStatus;

    /**
     * 工作评价0达标、1未达标、2超预期
     */
    @TableField("work_evaluate")
    private Integer workEvaluate;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 更新人
     */
    @TableField("updated_by")
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    private Date updatedTime;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;

    /**
     * 计划开始时间
     */
    @TableField("plan_start_time")
    private Date planStartTime;

    /**
     * 计划结束时间
     */
    @TableField("plan_end_time")
    private Date planEndTime;

    /**
     * 实际开始时间
     */
    @TableField("actual_start_time")
    private Date actualStartTime;

    /**
     * 实际结束时间
     */
    @TableField("actual_end_time")
    private Date actualEndTime;

    /**
     * 删除标识;0：正常，1已删除
     */
    @TableField("deleted")
    private Integer deleted;


}
