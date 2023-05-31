package com.team.performance.dto.publish;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="查询历史绩效列表对象信息",description="查询历史绩效列表对象信息")
public class QueryHistoryPerforPublishListDto {

    /**
     * 生产者id
     */
    private String productorId;

    /**
     * 年份
     */
    private String year;

    /**
     * 月份
     */
    private String month;

    /**
     * 季度
     */
    private Integer quarter;


    // sql查询条件
    // 班组编码
    private String teamCode;

    // 开始时间
    private Date startTime;

    // 结束时间
    private Date endTime;

    // 周期：1月，2季度
    private Integer assessmentCycle;

}
