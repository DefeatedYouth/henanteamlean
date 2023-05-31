package com.team.basic.service;

import com.team.basic.dto.CulturalTeamDynamicsDto;
import com.team.basic.entity.CulturalTeamDynamicsVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 班组动态表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface CulturalTeamDynamicsService extends IService<CulturalTeamDynamicsVo> {

    Integer addCulturalTeamDynamics(CulturalTeamDynamicsDto culturalTeamDynamicsDto);

    Integer updateCulturalTeamDynamics(CulturalTeamDynamicsDto culturalTeamDynamicsDto);

    Integer deleteCulturalTeamDynamics(CulturalTeamDynamicsDto culturalTeamDynamicsDto);

    List<CulturalTeamDynamicsVo> queryCulturalTeamDynamics(CulturalTeamDynamicsDto culturalTeamDynamicsDto);

}
