package com.team.basic.entity;

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
 * 工作业绩表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_cultural_work_performance")
public class CulturalWorkPerformanceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 业绩名称
     */
    @TableField("PERFORMANCE_NAME")
    private String performanceName;

    /**
     * 主要工作内容或重大项目内容
     */
    @TableField("WORK_CONTENT")
    private String workContent;

    /**
     * 人员ID
     */
    @TableField("PERSONNEL_ID")
    private String personnelId;

    /**
     * 人员
     */
    @TableField("PERSONNEL_NAME")
    private String personnelName;

    /**
     * 证明材料路径ID（与file_path表的path_id关联）
     */
    @TableField("SUPPORTING_MATERIALS_ID")
    private String supportingMaterialsId;

    /**
     * 备注
     */
    @TableField("REMARKS")
    private String remarks;

    /**
     * 发布状态（0：未发布，1：已发布）
     */
    @TableField("RELEASE_STATUS")
    private Integer releaseStatus;

    /**
     * 更新人ID
     */
    @TableField("UPDATED_ID")
    private String updatedId;

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
     * 创建人ID
     */
    @TableField("CREATED_ID")
    private String createdId;

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
     * 开始时间
     */
    @TableField("START_TIME")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("END_TIME")
    private Date endTime;

    /**
     * 是否删除
     */
    @TableField("IS_DELETE")
    private Integer isDelete;


}
