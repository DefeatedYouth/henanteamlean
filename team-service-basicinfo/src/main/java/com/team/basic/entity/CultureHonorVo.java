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
    * 荣誉称号表
    */
@Data
@TableName(value = "t_culture_honor")
public class CultureHonorVo {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 荣誉称号名称
     */
    @TableField(value = "honor_name")
    private String honorName;

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