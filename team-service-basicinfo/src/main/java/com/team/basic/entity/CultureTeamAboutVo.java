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
    * 班组简介表
    */
@Data
@TableName(value = "t_culture_team_about")
public class CultureTeamAboutVo {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
     * 班组成立时间
     */
    @TableField(value = "team_formed")
    private Date teamFormed;

    /**
     * 班组人数
     */
    @TableField(value = "team_peo_num")
    private Integer teamPeoNum;

    /**
     * 班组职责
     */
    @TableField(value = "team_duties")
    private String teamDuties;

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
     * 创建人ID
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