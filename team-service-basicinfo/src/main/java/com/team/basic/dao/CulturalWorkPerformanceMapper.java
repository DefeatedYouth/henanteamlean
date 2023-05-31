package com.team.basic.dao;

import com.team.basic.dto.CulturalWorkPerformanceDto;
import com.team.basic.entity.CulturalWorkPerformanceVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 工作业绩表 Mapper 接口
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface CulturalWorkPerformanceMapper extends BaseMapper<CulturalWorkPerformanceVo> {

    List<CulturalWorkPerformanceVo> queryCulturalWorkPerformance(CulturalWorkPerformanceDto culturalWorkPerformanceDto);

    Integer addCulturalWorkPerformance(CulturalWorkPerformanceDto culturalWorkPerformanceDto);

}
