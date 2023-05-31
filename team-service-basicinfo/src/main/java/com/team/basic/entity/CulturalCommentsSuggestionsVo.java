package com.team.basic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 意见建议表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_cultural_comments_suggestions")
public class CulturalCommentsSuggestionsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 提议类型
     */
    @TableField("PROPOSAL_TYPE")
    private String proposalType;

    /**
     * 附议人ID
     */
    @TableField("SECONDER_ID")
    private String seconderId;

    /**
     * 附议人
     */
    @TableField("SECONDER_NAME")
    private String seconderName;

    /**
     * 提议主题
     */
    @TableField("PROPOSED_THEME")
    private String proposedTheme;

    /**
     * 是否采纳(0：否 ，1：是)
     */
    @TableField("ADOPTED")
    private Integer adopted;

    /**
     * 采纳/不采纳 原因
     */
    @TableField("REASON")
    private String reason;

    /**
     * 采纳部门
     */
    @TableField("DEPARTMENT")
    private String department;

    /**
     * 附件路径ID（与file_path表path_id关联）
     */
    @TableField("ENCLOSURE_ID")
    private String enclosureId;

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
     * 是否删除
     */
    @TableField("IS_DELETE")
    private Integer isDelete;


}
