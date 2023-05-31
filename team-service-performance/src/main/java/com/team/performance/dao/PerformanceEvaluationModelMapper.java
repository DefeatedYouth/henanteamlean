package com.team.performance.dao;

import com.team.performance.dto.model.AddPerformanceModelDto;
import com.team.performance.dto.model.DeletePerformanceModelDto;
import com.team.performance.dto.model.QueryPerformanceModelListDto;
import com.team.performance.dto.monitorModel.QueryMonitorPerforModelListDto;
import com.team.performance.dto.monitorModel.QueryMonitorPerforModelRuleListDto;
import com.team.performance.entity.PerformanceEvaluationModelVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 绩效评价模板表 Mapper 接口
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface PerformanceEvaluationModelMapper extends BaseMapper<PerformanceEvaluationModelVo> {

    // 新增绩效评价模板表
    Integer addPerformanceEvaluationModel(PerformanceEvaluationModelVo performanceEvaluationModelVo);

    // 编辑绩效评价模板
    Integer editPerformanceEvaluationModel(PerformanceEvaluationModelVo performanceEvaluationModelVo);

    // 查询绩效评价模板列表总数
    int queryModelCount(QueryPerformanceModelListDto dto);

    // 分页查询绩效评价模板列表
    List<PerformanceEvaluationModelVo> queryModelListByPage(QueryPerformanceModelListDto dto);

    // 删除模型中的数据
    Integer deletePerformanceEvaluationModel(DeletePerformanceModelDto dto);

    // 发布绩效评价模板
    Integer publishPerformanceEvaluationModel(@Param(value = "model") PerformanceEvaluationModelVo model,
                                              @Param(value = "list") List<Integer> ids);

    // 批量修改班组信息-编辑绩效评价模板
    Integer editPerformanceEvaluationModelFromTeam(PerformanceEvaluationModelVo model);

    // 修改模板表中发布状态为已发布状态
    Integer updateModelPublishState();

    // 查询班长绩效评价模板列表总数
    int queryMonitorModelCount(QueryMonitorPerforModelListDto dto);

    // 分页班长查询绩效评价模板列表
    List<PerformanceEvaluationModelVo> queryMonitorModelListByPage(QueryMonitorPerforModelListDto dto);

    // 标准查询列表
    List<PerformanceEvaluationModelVo> queryMonitorPerforModelRuleList(QueryMonitorPerforModelRuleListDto modelDto);
}
