package com.team.performance.service;

import com.team.performance.dto.model.BatchChoosePerformanceModelTeamDto;
import com.team.performance.entity.PerformanceEvaluationModelVo;
import com.team.performance.entity.PerformanceModelTeamrelVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 绩效评价模板部分班组关系表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface PerformanceModelTeamrelService extends IService<PerformanceModelTeamrelVo> {

    // 批量选择适用班组
    Integer batchChooseTeam(BatchChoosePerformanceModelTeamDto dto);

    // 插入班组模板关系表
    Integer addModelTeamRelList(PerformanceEvaluationModelVo performanceEvaluationModelVo, List<PerformanceModelTeamrelVo> teamList);
}
