package com.team.performance.dao;

import com.team.performance.entity.PerformancePublishedTeamrelVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 已发布绩效评价部分班组关系表 Mapper 接口
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface PerformancePublishedTeamrelMapper extends BaseMapper<PerformancePublishedTeamrelVo> {

    // 再迁移关系表至发布关系表中
    Integer insetPublishedTeamFromMpdelTeam();

    // 修改发布关系表中的部分字段
    Integer updatePublishedTeam();
}
