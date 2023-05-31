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
 * 工单模板表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_work_model")
public class WorkModelVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模板编码
     */
    @TableField("model_no")
    private String modelNo;

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
     * 成员信息
     */
    @TableField("member_content")
    private String memberContent;

    /**
     * 标准积分
     */
    @TableField("norm_integral")
    private String normIntegral;

    /**
     * 周期
     */
    @TableField("period")
    private Integer period;

    /**
     * 周期类型(0天,1月)
     */
    @TableField("period_type")
    private Integer periodType;

    /**
     * 周期推送状态(0关,1开)
     */
    @TableField("period_status")
    private Integer periodStatus;

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
     * 上次派发日期
     */
    @TableField("last_push")
    private Date lastPush;

    /**
     * 删除标识;0：正常，1已删除
     */
    @TableField("deleted")
    private Integer deleted;


}
