package com.team.performance.dao;

import com.team.performance.dto.publish.QueryClassMemberPerforEvaListDto;
import com.team.performance.dto.publish.QueryHistoryPerforPublishListDto;
import com.team.performance.dto.publish.QueryPerforPublishModelListDto;
import com.team.performance.entity.PerformancePublishedInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 绩效评价已发布表 Mapper 接口
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface PerformancePublishedInfoMapper extends BaseMapper<PerformancePublishedInfoVo> {

    // 查询已发布的；当前时间大于发布时间的绩效模板；且适用班组为部分班组的；且未删除的绩效
    // 并先迁移主表至发布表中，并塞入时间
    Integer insertPublishedFromModel();

    // 查询历史绩效发布记录列表
    List<PerformancePublishedInfoVo> queryHistoryPerforPublishList(QueryHistoryPerforPublishListDto dto);

    // 根据当前查询月份或季度和班组编码查询对应的绩效模板列表
    List<PerformancePublishedInfoVo> queryPublishList(QueryClassMemberPerforEvaListDto dto);

    // 查询班员绩效评价标准
    List<PerformancePublishedInfoVo> queryPerforPublishModelList(QueryPerforPublishModelListDto dto);
}
