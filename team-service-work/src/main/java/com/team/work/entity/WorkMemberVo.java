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
 * 工单人员表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_work_member")
public class WorkMemberVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
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
     * 工单编码
     */
    @TableField("work_no")
    private String workNo;

    /**
     * 是否负责人(0不是、1是)
     */
    @TableField("leader_flag")
    private Integer leaderFlag;

    /**
     * 工作评价0达标、1未达标、2超预期
     */
    @TableField("work_evaluate")
    private Integer workEvaluate;

    /**
     * 累计积分
     */
    @TableField("total_integral")
    private String totalIntegral;

    /**
     * 调整积分
     */
    @TableField("adjust_integral")
    private String adjustIntegral;

    /**
     * 最终积分
     */
    @TableField("final_integral")
    private String finalIntegral;

    /**
     * 工作日期[ "20220721", "20220722"]
     */
    @TableField("work_days")
    private String workDays;

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
