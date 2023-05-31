package com.team.basic.dao;

import com.team.basic.dto.CulturalTeamDynamicsDto;
import com.team.basic.entity.CulturalTeamDynamicsVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 班组动态表 Mapper 接口
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface CulturalTeamDynamicsMapper extends BaseMapper<CulturalTeamDynamicsVo> {

    List<CulturalTeamDynamicsVo> queryCulturalTeamDynamics(CulturalTeamDynamicsDto culturalTeamDynamicsDto);

    Integer addCulturalTeamDynamics(CulturalTeamDynamicsDto culturalTeamDynamicsDto);

}
