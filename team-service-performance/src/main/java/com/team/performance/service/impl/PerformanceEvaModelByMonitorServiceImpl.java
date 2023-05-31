package com.team.performance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.team.common.constants.performance.PerforConstants;
import com.team.common.exception.CheckException;
import com.team.performance.dao.PerformanceEvaluationModelMapper;
import com.team.performance.dao.PerformanceModelTeamrelMapper;
import com.team.performance.dto.model.*;
import com.team.performance.dto.monitorModel.*;
import com.team.performance.entity.PerformanceEvaluationModelVo;
import com.team.performance.entity.PerformanceModelTeamrelVo;
import com.team.performance.service.PerformanceEvaModelByMonitorService;
import com.team.performance.service.PerformanceModelTeamrelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 班长-绩效评价模板表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
public class PerformanceEvaModelByMonitorServiceImpl extends ServiceImpl<PerformanceEvaluationModelMapper, PerformanceEvaluationModelVo> implements PerformanceEvaModelByMonitorService {

    @Resource
    PerformanceEvaluationModelMapper performanceEvaluationModelMapper;

    @Resource
    PerformanceModelTeamrelMapper performanceModelTeamrelMapper;

    @Autowired
    PerformanceModelTeamrelService performanceModelTeamrelServiceImpl;

    // 分页查询班长绩效评价模板列表
    @Override
    public Map<String, Object> queryMonitorPerforModelList(QueryMonitorPerforModelListDto dto) {
        // 返回map
        Map<String,Object> map = new HashMap<String,Object>();
        // todo 塞入当前班长角色所在班组
        dto.setTeamCode("5");
        dto.setProductorId(dto.getProductorId());
        // 查询班长绩效评价模板列表总数
        int count = performanceEvaluationModelMapper.queryMonitorModelCount(dto);
        // 分页班长查询绩效评价模板列表
        List<PerformanceEvaluationModelVo> list = new ArrayList<PerformanceEvaluationModelVo>();
        if(count > 0){
            list = performanceEvaluationModelMapper.queryMonitorModelListByPage(dto);
        }
        map.put("count",count);
        map.put("list",list);
        return map;
    }

    // 添加班长绩效评价模板
    @Override
    public Integer addMonitorPerforModel(AddMonitorPerforModelDto dto) {
        PerformanceEvaluationModelVo performanceEvaluationModelVo = getAddMonitorModelEntity(dto);
        // 默认未发布
        performanceEvaluationModelVo.setPublishState(PerforConstants.PUBLISH_STATE.NO_PUBLISH);
        performanceEvaluationModelVo.setIsHavTeam(PerforConstants.IS_HAV_TEAM.YES);
        // 绩效方案来源;1：工区专责，2：班长
        performanceEvaluationModelVo.setSource(PerforConstants.PERFOR_SOURCE.SOURCE_MONITOR);
        // todo 创建人编制单位、编制单位id
        performanceEvaluationModelVo.setCompanyName("编制单位");
        performanceEvaluationModelVo.setCompanyId("编制单位id");
        performanceEvaluationModelVo.setProductorId(dto.getProductorId());
        performanceEvaluationModelVo.setCreatedBy(dto.getCreatedBy());
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        performanceEvaluationModelVo.setCreatedTime(date);

        int count = performanceEvaluationModelMapper.addPerformanceEvaluationModel(performanceEvaluationModelVo);
        if(count <= 0){
            throw new CheckException("添加班长绩效评价模板失败");
        }

        List<PerformanceModelTeamrelVo> teamList = Lists.newArrayList();
        PerformanceModelTeamrelVo vo = new PerformanceModelTeamrelVo();
        // todo 创建人所属班组
        vo.setTeamCode("5");
        vo.setTeamName("班组5");
        teamList.add(vo);
        // 插入班组模板关系表
        performanceModelTeamrelServiceImpl.addModelTeamRelList(performanceEvaluationModelVo,teamList);

        return count;
    }

    public static PerformanceEvaluationModelVo getAddMonitorModelEntity(AddMonitorPerforModelDto dto) {
        if (dto == null) {
            return null;
        }
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

    // 编辑班长绩效评价模板
    @Override
    public Integer editMonitorPerforModel(EditMonitorPerforModelDto dto) {
        PerformanceEvaluationModelVo performanceEvaluationModelVo = getEditMonitorModelEntity(dto);
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        performanceEvaluationModelVo.setUpdatedTime(date);
        performanceEvaluationModelVo.setSource(PerforConstants.PERFOR_SOURCE.SOURCE_MONITOR);
        Integer count = performanceEvaluationModelMapper.editPerformanceEvaluationModel(performanceEvaluationModelVo);
        if(count <= 0 ){
            throw new CheckException("只可修改未发布状态下,非上级派发的细则！",
                    new Exception("只可修改未发布状态下,非上级派发的细则！"));
        }
        return count;
    }

    public static PerformanceEvaluationModelVo getEditMonitorModelEntity(EditMonitorPerforModelDto dto) {
        if (dto == null) {
            return null;
        }
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

    // 删除班长绩效评价模板
    @Override
    public Integer deleteMonitorPerforModel(DeleteMonitorPerforModelDto dto) {
        DeletePerformanceModelDto modelDto = new DeletePerformanceModelDto();
        modelDto.setUpdatedBy(dto.getUpdatedBy());
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        modelDto.setUpdatedTime(date);
        modelDto.setId(dto.getId());
        // 绩效方案来源2：班长
        modelDto.setSource(PerforConstants.PERFOR_SOURCE.SOURCE_MONITOR);
        // 删除模型中的数据(修改deleted为1)
        int count = performanceEvaluationModelMapper.deletePerformanceEvaluationModel(modelDto);
        if(count <= 0 ){
            throw new CheckException("只可删除未发布状态下,非上级派发的细则！",
                    new Exception("只可删除未发布状态下,非上级派发的细则！"));
        }
        // 删除关系表中数据
        PerformanceModelTeamrelVo teamrelVo = new PerformanceModelTeamrelVo();
        teamrelVo.setModelId(dto.getId());
        return performanceModelTeamrelMapper.deletePerformanceModelTeamRelByModelId(teamrelVo);
    }

    // 发布班长绩效评价模板
    @Override
    @Transactional
    public Integer publishMonitorPerforModel(PublishMonitorPerforModelDto dto) {
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        PerformanceEvaluationModelVo model = new PerformanceEvaluationModelVo();
        // 发布中状态
        model.setPublishState(PerforConstants.PUBLISH_STATE.ING_PUBLISTE);
        model.setPublishTime(date);
        model.setUpdatedBy(dto.getUpdatedBy());
        model.setUpdatedTime(date);
        model.setIsHavTeam(PerforConstants.IS_HAV_TEAM.YES);
        // 绩效方案来源;2：班长
        model.setSource(PerforConstants.PERFOR_SOURCE.SOURCE_MONITOR);
        int count = performanceEvaluationModelMapper.publishPerformanceEvaluationModel(model,dto.getIds());
        if(count <= 0 ){
            throw new CheckException("只可发布非上级派发，且是未发布状态下的细则！",
                    new Exception("只可发布非上级派发，且是未发布状态下的细则！"));
        }
        return count;
    }

    // 取消发布班长绩效评价模板
    @Override
    public Integer cancelcPublishMonitorPerforModel(CancelPublishMonitorPerforModelDto dto) {
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        PerformanceEvaluationModelVo model = new PerformanceEvaluationModelVo();
        // 未发布状态
        model.setPublishState(PerforConstants.PUBLISH_STATE.NO_PUBLISH);
        model.setPublishTime(null);
        model.setUpdatedBy(dto.getUpdatedBy());
        model.setUpdatedTime(date);
        // 绩效方案来源;2：班长
        model.setSource(PerforConstants.PERFOR_SOURCE.SOURCE_MONITOR);
        int count = performanceEvaluationModelMapper.publishPerformanceEvaluationModel(model,dto.getIds());
        if(count <= 0 ){
            throw new CheckException("只可取消发布非上级派发的细则！",
                    new Exception("只可取消发布非上级派发的细则！"));
        }
        return count;
    }

    // 标准查询列表
    @Override
    public List<PerformanceEvaluationModelVo> queryMonitorPerforModelRuleList(QueryMonitorPerforModelRuleListDto dto) {
        // todo 塞入班长所在班组编码
        dto.setTeamCode("5");
        // 绩效方案来源;2：班长
        dto.setSource(PerforConstants.PERFOR_SOURCE.SOURCE_MONITOR);
        // 标准查看：查看当前发布中和已发布状态的本班组细则。
        return performanceEvaluationModelMapper.queryMonitorPerforModelRuleList(dto);
    }
}
