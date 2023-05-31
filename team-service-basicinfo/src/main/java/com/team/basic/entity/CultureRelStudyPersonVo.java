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
    * 学习人员关联表
    */
@Data
@TableName(value = "t_culture_rel_study_person")
public class CultureRelStudyPersonVo {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学习文件id
     */
    @TableField(value = "study_document_id")
    private Integer studyDocumentId;

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
     * 学习人员id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 学习人员名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 是否打卡<1>已打卡<0>未打卡
     */
    @TableField(value = "is_study")
    private Integer isStudy;

    /**
     * 学习笔记
     */
    @TableField(value = "study_notes")
    private String studyNotes;

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