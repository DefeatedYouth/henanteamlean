package com.team.performance.dao;

import com.team.performance.dto.publish.QueryClassMemberPerforEvaListDto;
import com.team.performance.entity.PerformanceRecordVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 绩效评价记录表 Mapper 接口
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface PerformanceRecordMapper extends BaseMapper<PerformanceRecordVo> {

    // 查询最近一次已发布的绩效评价
    List<PerformanceRecordVo> queryClassMemberPerforEvaList(QueryClassMemberPerforEvaListDto dto);

    // 查询评价记录
    List<PerformanceRecordVo> queryClassMemberPerforEvaRecordList(QueryClassMemberPerforEvaListDto dto);
}
