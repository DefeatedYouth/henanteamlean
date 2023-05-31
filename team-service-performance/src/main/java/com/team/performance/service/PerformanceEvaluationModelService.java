package com.team.performance.service;

import com.team.performance.dto.model.*;
import com.team.performance.entity.PerformanceEvaluationModelVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 绩效评价模板表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface PerformanceEvaluationModelService extends IService<PerformanceEvaluationModelVo> {

    // 新增绩效评价模板表
    Integer addPerformanceEvaluationModel(AddPerformanceModelDto dto);

    // 编辑绩效评价模板
    Integer editPerformanceEvaluationModel(EditPerformanceModelDto dto);

    // 分页查询绩效评价模板列表
    Map<String, Object> queryPerformanceModelList(QueryPerformanceModelListDto dto);

    // 删除绩效评价模板
    Integer deletePerformanceEvaluationModel(DeletePerformanceModelDto dto);

    // 发布绩效评价模板
    Integer publishPerformanceEvaluationModel(PublishPerformanceModelDto dto);

    // 取消发布绩效评价模板
    Integer cancelcPublishPerformanceModel(CancelPublishPerformanceModelDto dto);
}
