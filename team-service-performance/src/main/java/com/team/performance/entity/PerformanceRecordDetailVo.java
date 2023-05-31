package com.team.performance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 绩效评价记录表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_performance_record_detail")
public class PerformanceRecordDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 绩效评价记录表流水号
     */
    @TableField("RECORD_ID")
    private String recordId;

    /**
     * 指标名称
     */
    @TableField("QUATE_NAME")
    private String quateName;

    /**
     * 说明描述
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 积分
     */
    @TableField("INTEGRAL")
    private BigDecimal integral;


    /**
     * 流水号id,返回字段
     */
    @TableId(value = "detailId")
    private Integer detailId;

}
