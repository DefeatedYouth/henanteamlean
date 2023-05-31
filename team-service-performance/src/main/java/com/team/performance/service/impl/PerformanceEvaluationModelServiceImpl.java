package com.team.performance.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.team.common.constants.performance.PerforConstants;
import com.team.common.exception.CheckException;
import com.team.performance.dao.PerformanceModelTeamrelMapper;
import com.team.performance.dto.model.*;
import com.team.performance.entity.PerformanceEvaluationModelVo;
import com.team.performance.dao.PerformanceEvaluationModelMapper;
import com.team.performance.entity.PerformanceModelTeamrelVo;
import com.team.performance.service.PerformanceEvaluationModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工区专责-绩效评价模板表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
public class PerformanceEvaluationModelServiceImpl extends ServiceImpl<PerformanceEvaluationModelMapper, PerformanceEvaluationModelVo> implements PerformanceEvaluationModelService {

    @Resource
    PerformanceEvaluationModelMapper performanceEvaluationModelMapper;

    @Resource
    PerformanceModelTeamrelMapper performanceModelTeamrelMapper;

    // 分页查询绩效评价模板列表
    @Override
    public Map<String, Object> queryPerformanceModelList(QueryPerformanceModelListDto dto) {
        // 返回map
        Map<String,Object> map = Maps.newHashMap();
        // 查询绩效评价模板列表总数
        int count = performanceEvaluationModelMapper.queryModelCount(dto);
        // 分页查询绩效评价模板列表
        List<PerformanceEvaluationModelVo> list = Lists.newArrayList();
        if(count > 0){
            list = performanceEvaluationModelMapper.queryModelListByPage(dto);
        }
        map.put("count",count);
        map.put("list",list);
        return map;
    }

    // 新增绩效评价模板表
    @Override
    public Integer addPerformanceEvaluationModel(AddPerformanceModelDto dto) {
        PerformanceEvaluationModelVo performanceEvaluationModelVo = getAddModelEntity(dto);
        // 默认未发布
        performanceEvaluationModelVo.setPublishState(PerforConstants.PUBLISH_STATE.NO_PUBLISH);
        // 是否含有班组：1不包含，2包含
        performanceEvaluationModelVo.setIsHavTeam(PerforConstants.IS_HAV_TEAM.NO);
        // 绩效方案来源;1：工区专责，2：班长
        performanceEvaluationModelVo.setSource(PerforConstants.PERFOR_SOURCE.SOURCE_LEADER);
        // todo 创建人编制单位、编制单位id
        performanceEvaluationModelVo.setCompanyName("编制单位");
        performanceEvaluationModelVo.setCompanyId("编制单位id");
        performanceEvaluationModelVo.setProductorId(dto.getProductorId());
        performanceEvaluationModelVo.setCreatedBy(dto.getCreatedBy());
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        performanceEvaluationModelVo.setCreatedTime(date);

        return performanceEvaluationModelMapper.addPerformanceEvaluationModel(performanceEvaluationModelVo);
    }

    public static PerformanceEvaluationModelVo getAddModelEntity(AddPerformanceModelDto dto) {
        PerformanceEvaluationModelVo performanceEvaluationModelVo = new PerformanceEvaluationModelVo();
        performanceEvaluationModelVo.setQuateName(dto.getQuateName());
        performanceEvaluationModelVo.setDetailRule(dto.getDetailRule());
        performanceEvaluationModelVo.setAssessmentCycle(dto.getAssessmentCycle());
        performanceEvaluationModelVo.setAssessor(dto.getAssessor());
        performanceEvaluationModelVo.setAssessmentObject(dto.getAssessmentObject());
        performanceEvaluationModelVo.setIntegral(dto.getIntegral());
        performanceEvaluationModelVo.setProductorId(dto.getProductorId());
        performanceEvaluationModelVo.setCreatedBy(dto.getCreatedBy());
        return performanceEvaluationModelVo;
    }

    // 编辑绩效评价模板
    @Override
    public Integer editPerformanceEvaluationModel(EditPerformanceModelDto dto) {
        PerformanceEvaluationModelVo performanceEvaluationModelVo = getEditModelEntity(dto);
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        performanceEvaluationModelVo.setUpdatedTime(date);
        // 绩效方案来源;1：工区专责
        performanceEvaluationModelVo.setSource(PerforConstants.PERFOR_SOURCE.SOURCE_LEADER);
        Integer count = performanceEvaluationModelMapper.editPerformanceEvaluationModel(performanceEvaluationModelVo);
        if(count <= 0 ){
            throw new CheckException("只可修改未发布状态下的细则！",
                    new Exception("只可修改未发布状态下的细则！"));
        }
        return count;
    }

    public static PerformanceEvaluationModelVo getEditModelEntity(EditPerformanceModelDto dto) {
        PerformanceEvaluationModelVo performanceEvaluationModelVo = new PerformanceEvaluationModelVo();
        performanceEvaluationModelVo.setId(dto.getId());
        performanceEvaluationModelVo.setQuateName(dto.getQuateName());
        performanceEvaluationModelVo.setDetailRule(dto.getDetailRule());
        performanceEvaluationModelVo.setAssessmentCycle(dto.getAssessmentCycle());
        performanceEvaluationModelVo.setAssessor(dto.getAssessor());
        performanceEvaluationModelVo.setAssessmentObject(dto.getAssessmentObject());
        performanceEvaluationModelVo.setIntegral(dto.getIntegral());
        performanceEvaluationModelVo.setUpdatedBy(dto.getUpdatedBy());
        return performanceEvaluationModelVo;
    }

    // 删除绩效评价模板
    @Override
    public Integer deletePerformanceEvaluationModel(DeletePerformanceModelDto dto) {
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        dto.setUpdatedTime(date);
        // 绩效方案来源;1：工区专责
        dto.setSource(PerforConstants.PERFOR_SOURCE.SOURCE_LEADER);
        // 删除模型中的数据(修改deleted为1)
        int count = performanceEvaluationModelMapper.deletePerformanceEvaluationModel(dto);
        if(count <= 0 ){
            throw new CheckException("只可删除未发布状态下的细则！",
                    new Exception("只可删除未发布状态下的细则！"));
        }
        // 删除关系表中数据
        PerformanceModelTeamrelVo teamrelVo = new PerformanceModelTeamrelVo();
        teamrelVo.setModelId(dto.getId());
        return performanceModelTeamrelMapper.deletePerformanceModelTeamRelByModelId(teamrelVo);
    }

    // 发布绩效评价模板
    @Override
    @Transactional
    public Integer publishPerformanceEvaluationModel(PublishPerformanceModelDto dto) {
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        PerformanceEvaluationModelVo model = new PerformanceEvaluationModelVo();
        // 发布中状态
        model.setPublishState(PerforConstants.PUBLISH_STATE.ING_PUBLISTE);
        model.setPublishTime(date);
        model.setUpdatedBy(dto.getUpdatedBy());
        model.setUpdatedTime(date);
        model.setIsHavTeam(PerforConstants.IS_HAV_TEAM.YES);
        // 绩效方案来源;1：工区专责
        model.setSource(PerforConstants.PERFOR_SOURCE.SOURCE_LEADER);
        int count = performanceEvaluationModelMapper.publishPerformanceEvaluationModel(model,dto.getIds());
        if(count <= 0 ){
            throw new CheckException("只可发布适用班组不为空，且是未发布状态下的细则！",
                    new Exception("只可发布适用班组不为空，且是未发布状态下的细则！"));
        }
        return count;
    }

    // 取消发布绩效评价模板
    @Override
    public Integer cancelcPublishPerformanceModel(CancelPublishPerformanceModelDto dto) {
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        PerformanceEvaluationModelVo model = new PerformanceEvaluationModelVo();
        // 未发布状态
        model.setPublishState(PerforConstants.PUBLISH_STATE.NO_PUBLISH);
        model.setPublishTime(null);
        model.setUpdatedBy(dto.getUpdatedBy());
        model.setUpdatedTime(date);
        // 绩效方案来源;1：工区专责
        model.setSource(PerforConstants.PERFOR_SOURCE.SOURCE_LEADER);
        return performanceEvaluationModelMapper.publishPerformanceEvaluationModel(model,dto.getIds());
    }
}
