package com.team.performance.dao;

import com.team.performance.dto.model.BatchChoosePerformanceModelTeamDto;
import com.team.performance.dto.model.DeletePerformanceModelDto;
import com.team.performance.entity.PerformanceModelTeamrelVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 绩效评价模板部分班组关系表 Mapper 接口
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface PerformanceModelTeamrelMapper extends BaseMapper<PerformanceModelTeamrelVo> {

    // 删除关系表中数据(真删)
    int deletePerformanceModelTeamRelByModelId(PerformanceModelTeamrelVo teamrelVo);

    // 批量选择适用班组
    Integer batchChooseTeam(@Param(value = "teamVo") PerformanceModelTeamrelVo teamVo,
                            @Param(value = "teamList") List<PerformanceModelTeamrelVo> teamList);
}
