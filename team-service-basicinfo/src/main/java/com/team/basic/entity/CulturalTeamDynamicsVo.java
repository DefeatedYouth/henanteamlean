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
 * 班组动态表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_cultural_team_dynamics")
public class CulturalTeamDynamicsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 班组动态标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 班组动态描述
     */
    @TableField("DYNAMIC_DESCRIPTION")
    private String dynamicDescription;

    /**
     * 班组图片路径ID（与file_path表的path_id关联
     */
    @TableField("PICTURE_ID")
    private String pictureId;

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
     * 是否删除
     */
    @TableField("IS_DELETE")
    private Integer isDelete;


}
