package com.team.performance.entity;

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
 * 已发布绩效评价部分班组关系表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_performance_published_teamrel")
public class PerformancePublishedTeamrelVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 已发布绩效评价流水号
     */
    @TableField("PUBLISHED_ID")
    private String publishedId;

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

    /**
     * 生成者id
     */
    @TableField("PRODUCTOR_ID")
    private String productorId;


}
