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
 * @Date:    2022/8/5 10:33   
 * @Version:    1.0     
 */

/**
    * 节假日表
    */
@Data
@TableName(value = "t_sys_holidays")
public class SysHolidaysVo {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 节假日名称
     */
    @TableField(value = "holidays_name")
    private String holidaysName;

    /**
     * 节假日开始时间
     */
    @TableField(value = "start_time")
    private Date startTime;

    /**
     * 节假日结束时间
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 节假日天数
     */
    @TableField(value = "holidays_days")
    private Integer holidaysDays;

    /**
     * 调休时间：示例2022-05-03;2022-06-02
     */
    @TableField(value = "days_off_date")
    private String daysOffDate;

    /**
     * 节假日所在年份
     */
    @TableField(value = "\"year\"")
    private Integer year;

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