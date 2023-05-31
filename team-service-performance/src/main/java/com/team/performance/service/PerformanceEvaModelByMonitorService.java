package com.team.performance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.performance.dto.monitorModel.*;
import com.team.performance.entity.PerformanceEvaluationModelVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 班长-绩效评价模板表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface PerformanceEvaModelByMonitorService extends IService<PerformanceEvaluationModelVo> {

    // 分页查询班长绩效评价模板列表
    Map<String, Object> queryMonitorPerforModelList(QueryMonitorPerforModelListDto dto);

    // 添加班长绩效评价模板
    Integer addMonitorPerforModel(AddMonitorPerforModelDto dto);

    // 编辑班长绩效评价模板
    Integer editMonitorPerforModel(EditMonitorPerforModelDto dto);

    // 删除班长绩效评价模板
    Integer deleteMonitorPerforModel(DeleteMonitorPerforModelDto dto);

    // 发布班长绩效评价模板
    Integer publishMonitorPerforModel(PublishMonitorPerforModelDto dto);

    // 取消发布班长绩效评价模板
    Integer cancelcPublishMonitorPerforModel(CancelPublishMonitorPerforModelDto dto);

    // 标准查询列表
    List<PerformanceEvaluationModelVo> queryMonitorPerforModelRuleList(QueryMonitorPerforModelRuleListDto dto);
}
