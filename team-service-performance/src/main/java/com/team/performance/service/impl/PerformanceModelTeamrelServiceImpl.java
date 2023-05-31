package com.team.performance.service.impl;

import com.google.common.collect.Lists;
import com.team.performance.dao.PerformanceEvaluationModelMapper;
import com.team.performance.dto.model.BatchChoosePerformanceModelTeamDto;
import com.team.performance.entity.PerformanceEvaluationModelVo;
import com.team.performance.entity.PerformanceModelTeamrelVo;
import com.team.performance.dao.PerformanceModelTeamrelMapper;
import com.team.performance.service.PerformanceModelTeamrelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 绩效评价模板部分班组关系表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
public class PerformanceModelTeamrelServiceImpl extends ServiceImpl<PerformanceModelTeamrelMapper, PerformanceModelTeamrelVo> implements PerformanceModelTeamrelService {

    @Resource
    PerformanceModelTeamrelMapper performanceModelTeamrelMapper;

    @Resource
    PerformanceEvaluationModelMapper performanceEvaluationModelMapper;

    // 批量选择适用班组
    @Override
    public Integer batchChooseTeam(BatchChoosePerformanceModelTeamDto dto) {
        // 先删除关系表中的数据
        // 删除关系表中数据(真删)
        PerformanceModelTeamrelVo teamrelVo = new PerformanceModelTeamrelVo();
        teamrelVo.setModelId(dto.getModelId());
        performanceModelTeamrelMapper.deletePerformanceModelTeamRelByModelId(teamrelVo);
        // 再插入数据
        PerformanceModelTeamrelVo teamVo = new PerformanceModelTeamrelVo();
        teamVo.setModelId(dto.getModelId());

        // 班组编码
        List<String> teamCodeList = Arrays.asList(dto.getTeamCodes().split(","));
        // 班组名称
        List<String> teamNameList = Arrays.asList(dto.getTeamNames().split(","));

        List<PerformanceModelTeamrelVo> teamList = Lists.newArrayList();

        for(int i = 0;i<teamCodeList.size();i++){
            PerformanceModelTeamrelVo vo = new PerformanceModelTeamrelVo();
            vo.setTeamCode(teamCodeList.get(i));
            vo.setTeamName(teamNameList.get(i));
            teamList.add(vo);
        }

        // 批量插入关系表
        performanceModelTeamrelMapper.batchChooseTeam(teamVo,teamList);

        // 修改模板表中的班组类型为部分班组
        PerformanceEvaluationModelVo model = new PerformanceEvaluationModelVo();
        model.setId(dto.getModelId());
        // 班组类型：2部分班组
        model.setIsHavTeam(2);
        model.setUpdatedBy(dto.getUpdatedBy());
        // 获取当前时间
        Date date = new Date(System.currentTimeMillis());
        model.setUpdatedTime(date);
        return performanceEvaluationModelMapper.editPerformanceEvaluationModelFromTeam(model);
    }

    // 插入班组模板关系表
    @Override
    public Integer addModelTeamRelList(PerformanceEvaluationModelVo performanceEvaluationModelVo, List<PerformanceModelTeamrelVo> teamList) {
        PerformanceModelTeamrelVo teamVo = new PerformanceModelTeamrelVo();
        teamVo.setModelId(performanceEvaluationModelVo.getId());
        // 批量插入关系表
        return performanceModelTeamrelMapper.batchChooseTeam(teamVo,teamList);
    }
}
