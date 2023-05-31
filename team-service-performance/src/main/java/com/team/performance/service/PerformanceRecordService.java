package com.team.performance.service;

import com.team.performance.dto.publish.QueryClassMemberPerforEvaListDto;
import com.team.performance.entity.PerformanceRecordVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 绩效评价记录表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface PerformanceRecordService extends IService<PerformanceRecordVo> {

    // 查询班员绩效评价
    List<PerformanceRecordVo> queryClassMemberPerforEvaList(QueryClassMemberPerforEvaListDto dto);
}
