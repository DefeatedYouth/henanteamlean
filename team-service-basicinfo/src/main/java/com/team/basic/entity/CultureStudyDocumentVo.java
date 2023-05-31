package com.team.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

/**
 * @Description:       
 * @Date:    2022/8/5 10:34   
 * @Version:    1.0     
 */

/**
    * 学习文件表
    */
@Data
@TableName(value = "t_culture_study_document")
public class CultureStudyDocumentVo {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学习文件名称
     */
    @TableField(value = "study_document_name")
    private String studyDocumentName;

    /**
     * 下发文件类型（标签）
     */
    @TableField(value = "type_name")
    private String typeName;

    /**
     * 班组id
     */
    @TableField(value = "team_id")
    private String teamId;

    /**
     * 班组名称
     */
    @TableField(value = "team_name")
    private String teamName;

    /**
     * 已打卡人数
     */
    @TableField(value = "read_count")
    private String readCount;

    /**
     * 发布状态<1>已发布<0>未发布
     */
    @TableField(value = "publish_status")
    private Integer publishStatus;

    /**
     * 发布日期
     */
    @TableField(value = "publish_date")
    private Date publishDate;

    /**
     * 发布人id
     */
    @TableField(value = "publish_person_id")
    private String publishPersonId;

    /**
     * 发布人
     */
    @TableField(value = "publish_person_name")
    private String publishPersonName;

    /**
     * 学习要求
     */
    @TableField(value = "study_require")
    private String studyRequire;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 更新人ID
     */
    @TableField(value = "updated_id")
    private String updatedId;

    /**
     * 更新人
     */
    @TableField(value = "updated_by")
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time")
    private Date updatedTime;

    /**
     * 生产者ID
     */
    @TableField(value = "producer_id")
    private String producerId;

    /**
     * 创建人
     */
    @TableField(value = "created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    private Date createdTime;

    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}