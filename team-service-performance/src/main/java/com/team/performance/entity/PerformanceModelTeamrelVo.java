package com.team.performance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 绩效评价模板部分班组关系表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_performance_model_teamrel")
public class PerformanceModelTeamrelVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 绩效评价模板流水号
     */
    @TableField("evaluation_model_id")
    private Integer modelId;

    /**
     * 班组编码
     */
    @TableField("TEAM_CODE")
    private String teamCode;

    /**
     * 班组名称
     */
    @TableField("TEAM_NAME")
    private String teamName;

}
