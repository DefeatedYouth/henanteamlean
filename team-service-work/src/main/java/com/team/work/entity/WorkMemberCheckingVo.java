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
 * 工单员工考勤表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_work_member_checking")
public class WorkMemberCheckingVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 成员ID
     */
    @TableField("member_id")
    private String memberId;

    /**
     * 成员名称
     */
    @TableField("member_name")
    private String memberName;

    /**
     * 工作日期"20220722"
     */
    @TableField("work_day")
    private Integer workDay;

    /**
     * 工单编码
     */
    @TableField("work_no")
    private String workNo;

    /**
     * 工作类型(0班组建设、1员工培训、2安全活动、3现场生产、4基建验收、5基础管理、6科技创新、7综合事务、8临时任务、9其他)
     */
    @TableField("work_type")
    private Integer workType;

    /**
     * 工作状态(0出勤、1出差、2培训、3借调、4缺勤)
     */
    @TableField("work_status")
    private Integer workStatus;

    /**
     * 请假类型(0旷工、1事假、2病假、3婚假、4丧假、5产假、6探亲假、7年休假、8护理假、9疗休养)
     */
    @TableField("leave_type")
    private Integer leaveType;

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
     * 删除标识;0：正常，1已删除
     */
    @TableField("deleted")
    private Integer deleted;

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
     * 备注
     */
    @TableField("remark")
    private String remark;

}
