package com.team.basic.service;

import com.team.basic.dto.CulturalWorkPerformanceDto;
import com.team.basic.entity.CulturalWorkPerformanceVo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 工作业绩表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface CulturalWorkPerformanceService extends IService<CulturalWorkPerformanceVo> {

    Integer addCulturalWorkPerformance(CulturalWorkPerformanceDto culturalWorkPerformanceDto);

    Integer updateCulturalWorkPerformance(CulturalWorkPerformanceDto culturalWorkPerformanceDto);

    Integer deleteCulturalWorkPerformance(CulturalWorkPerformanceDto culturalWorkPerformanceDto);

    List<CulturalWorkPerformanceVo> queryCulturalWorkPerformance(CulturalWorkPerformanceDto culturalWorkPerformanceDto);

    void exportExcelCulturalWorkPerformance(CulturalWorkPerformanceDto culturalWorkPerformanceDto, HttpServletResponse response);

}
