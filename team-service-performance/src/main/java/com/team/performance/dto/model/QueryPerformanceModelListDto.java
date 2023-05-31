package com.team.performance.dto.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询专责绩效对象信息列表
 * @author zhangm
 * @date 2022/7/27$
 */
@Data
public class QueryPerformanceModelListDto {

    /**
     * 指标名称
     */
    private String quateName;

    /**
     * 考核周期;1：月，2：季度
     */
    private Integer assessmentCycle;

    /**
     * 班组编码
     */
    private String teamCode;

    /**
     * 发布状态：1未发布，2发布
     */
    private Integer publishState;

    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 生产者id
     */
    private String productorId;
}
