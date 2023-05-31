package com.team.performance.dto.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 删除专责绩效对象信息
 * @author zhangm
 * @date 2022/7/27$
 */
@Data
public class DeletePerformanceModelDto {

    /**
     * 流水号
     */
    private Integer id;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 绩效方案来源;1：工区专责，2：班长
     */
    private Integer source;
}
