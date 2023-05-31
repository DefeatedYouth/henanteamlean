package com.team.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @Description:       
 * @Date:    2022/8/1 20:43   
 * @Version:    1.0     
 */

/**
    * 变电站字典表
    */
@Data
@TableName(value = "t_dict_station")
public class TDictStationVo {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 总公司名称
     */
    @TableField(value = "hq_name")
    private String hqName;

    /**
     * 总公司id
     */
    @TableField(value = "hq_id")
    private String hqId;

    /**
     * 单位名称
     */
    @TableField(value = "unit_name")
    private String unitName;

    /**
     * 单位id
     */
    @TableField(value = "unit_id")
    private String unitId;

    /**
     * 部门名称
     */
    @TableField(value = "department_name")
    private String departmentName;

    /**
     * 部门id
     */
    @TableField(value = "department_id")
    private String departmentId;

    /**
     * 电压等级
     */
    @TableField(value = "voltage_level_name")
    private String voltageLevelName;

    /**
     * 电压等级id
     */
    @TableField(value = "voltage_level_id")
    private String voltageLevelId;

    /**
     * 变电站id
     */
    @TableField(value = "station_id")
    private String stationId;

    /**
     * 变电站名称
     */
    @TableField(value = "station_name")
    private String stationName;

    /**
     * 创建人
     */
    @TableField(value = "created_by")
    private String createdBy;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time")
    private LocalDateTime updatedTime;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField(value = "updated_by")
    private String updatedBy;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;
}