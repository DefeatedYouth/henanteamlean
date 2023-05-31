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
 * 工单操作记录表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_work_record")
public class WorkRecordVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 工单编码
     */
    @TableField("work_no")
    private String workNo;

    /**
     * 工作状态0新建、1待执行、2执行中、3已完成、4已归档、5已取消
     */
    @TableField("work_status")
    private Integer workStatus;

    /**
     * 操作类型0新建、1删除、2修改、4取消、5任务发布、6人员调整、7评价调整、8归档
     */
    @TableField("operate_type")
    private Integer operateType;

    /**
     * 操作内容
     */
    @TableField("operate_content")
    private String operateContent;

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


}
