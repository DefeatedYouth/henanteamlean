package com.team.performance.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.team.common.constants.performance.PerforConstants;
import com.team.common.exception.CheckException;
import com.team.performance.dto.publish.QueryHistoryPerforPublishListDto;
import com.team.performance.dto.publish.QueryPerforPublishModelListDto;
import com.team.performance.entity.PerformancePublishedInfoVo;
import com.team.performance.dao.PerformancePublishedInfoMapper;
import com.team.performance.service.PerformancePublishedInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 绩效评价已发布表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
public class PerformancePublishedInfoServiceImpl extends ServiceImpl<PerformancePublishedInfoMapper, PerformancePublishedInfoVo> implements PerformancePublishedInfoService {

    @Resource
    PerformancePublishedInfoMapper performancePublishedInfoMapper;

    // 查询历史绩效发布记录列表
    @Override
    public List<PerformancePublishedInfoVo> queryHistoryPerforPublishList(QueryHistoryPerforPublishListDto dto) {
        // todo 根据当前登录人信息查询所在班组编码
        dto.setTeamCode("5");
        // 年份
        String year = dto.getYear();
        // 月份
        String month = dto.getMonth();
        // 季度
        Integer quarter = dto.getQuarter();

        // 处理时间
        Map<String,Object> map = this.getQueryTime(year,month,quarter);

        // 开始时间
        dto.setStartTime(ObjectUtil.isNotEmpty(map.get("startTime")) ? (Date) map.get("startTime") : null);
        // 结束时间
        dto.setEndTime(ObjectUtil.isNotEmpty(map.get("endTime")) ? (Date) map.get("endTime") : null);
        // 周期：1月，2季度
        dto.setAssessmentCycle(ObjectUtil.isNotEmpty(map.get("assessmentCycle")) ? (Integer) map.get("assessmentCycle") : null);

        return performancePublishedInfoMapper.queryHistoryPerforPublishList(dto);
    }

    // 处理查询时间
    @Override
    public Map<String,Object> getQueryTime(String year, String month, Integer quarter)
    {
        Map<String,Object> map = Maps.newHashMap();
        // 周期：1月，2季度
        Integer assessmentCycle = null;
        // 开始时间
        Date startTime = null;
        // 结束时间
        Date endTime = null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar time = Calendar.getInstance();
            // 开始时间
            String queryTime = Strings.EMPTY;
            // 结束时间
            String queryEndTime = Strings.EMPTY;
            // 月份
            if(StrUtil.isNotEmpty(month))
            {
                // 查询时间（因为发布表的showTime为实际发布时间，则比考核月份多一个月，则当前月份需要加一个月再与showTime比较）
                // 开始时间
                queryTime = year+"-"+month+"-01 00:00:00";
                // 结束时间
                queryEndTime = year+"-"+month+"-01 23:59:59";

                assessmentCycle = PerforConstants.ASSESSMENT_CYCLE.MONTH;
            }
            else if(ObjectUtil.isNotNull(quarter) && quarter > 0)
            {
                switch (quarter){
                    case 1 :
                        // 开始时间
                        queryTime = year+"-01-01 00:00:00";
                        // 结束时间
                        queryEndTime = year+"-03-01 23:59:59";
                        break;
                    case 2 :
                        // 开始时间
                        queryTime = year+"-04-01 00:00:00";
                        // 结束时间
                        queryEndTime = year+"-06-01 23:59:59";
                        break;
                    case 3 :
                        // 开始时间
                        queryTime = year+"-07-01 00:00:00";
                        // 结束时间
                        queryEndTime = year+"-09-01 23:59:59";
                        break;
                    case 4 :
                        // 开始时间
                        queryTime = year+"-10-01 00:00:00";
                        // 结束时间
                        queryEndTime = year+"-12-01 23:59:59";
                        break;
                }
                // 季度
                assessmentCycle = PerforConstants.ASSESSMENT_CYCLE.QUARTER;
            }
            else{
                // 开始时间
                queryTime = year +"-01-01 00:00:00";
                // 结束时间
                queryEndTime = year +"-12-01 23:59:59";
            }
            // 开始时间、结束时间处理
            if(StrUtil.isNotEmpty(queryTime) && StrUtil.isNotEmpty(queryEndTime))
            {
                time.setTime(sdf.parse(queryTime));
                time.add(Calendar.MONTH,1);
                startTime = time.getTime();

                time.setTime(sdf.parse(queryEndTime));
                time.add(Calendar.MONTH,1);
                endTime = time.getTime();
            }
            map.put("assessmentCycle",assessmentCycle);
            map.put("startTime",startTime);
            map.put("endTime",endTime);

        } catch (ParseException e) {
            throw new CheckException(e.getMessage(),new Exception(e.getMessage()));
        }
        return map;
    }

    // 查询班员绩效评价标准
    @Override
    public List<PerformancePublishedInfoVo> queryPerforPublishModelList(QueryPerforPublishModelListDto dto) {
        // todo 根据当前登录人信息查询所在班组编码
        dto.setTeamCode("5");
        // 年份
        String year = dto.getYear();
        // 月份
        String month = dto.getMonth();
        // 季度
        Integer quarter = dto.getQuarter();

        // 处理时间
        if(StrUtil.isNotEmpty(year)){
            Map<String,Object> map = this.getQueryTime(year,month,quarter);

            // 开始时间
            dto.setStartTime(ObjectUtil.isNotEmpty(map.get("startTime")) ? (Date) map.get("startTime") : null);
            // 结束时间
            dto.setEndTime(ObjectUtil.isNotEmpty(map.get("endTime")) ? (Date) map.get("endTime") : null);
            // 周期：1月，2季度
            dto.setAssessmentCycle(ObjectUtil.isNotEmpty(map.get("assessmentCycle")) ? (Integer) map.get("assessmentCycle") : null);
        }
        return performancePublishedInfoMapper.queryPerforPublishModelList(dto);
    }
}
