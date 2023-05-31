package com.team.performance.service;

import com.team.performance.dto.publish.QueryHistoryPerforPublishListDto;
import com.team.performance.dto.publish.QueryPerforPublishModelListDto;
import com.team.performance.entity.PerformancePublishedInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 绩效评价已发布表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface PerformancePublishedInfoService extends IService<PerformancePublishedInfoVo> {

    // 查询历史绩效发布记录列表
    List<PerformancePublishedInfoVo> queryHistoryPerforPublishList(QueryHistoryPerforPublishListDto dto);

    // 处理查询时间
    Map<String, Object> getQueryTime(String year, String month, Integer quarter);

    // 查询班员绩效评价标准
    List<PerformancePublishedInfoVo> queryPerforPublishModelList(QueryPerforPublishModelListDto dto);
}
