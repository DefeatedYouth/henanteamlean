package com.team.work.dao;

import com.team.work.dto.*;
import com.team.work.entity.WorkMemberCheckingVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工单员工考勤表 Mapper 接口
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface WorkMemberCheckingMapper extends BaseMapper<WorkMemberCheckingVo> {

    List<PersonPlanDto> queryOnJobCount(Map month);

    List<WorkPlanDto> queryWorkTypeCount(Map queryMap);

    List<CalendarHeadInfoDto> queryCalendarHeadInfo(Map queryMap);

    List<WorkCalendarPlanDto> queryCalendarBodyInfo(Map queryMap);

    List<WorkCalendarPlanDto> queryCalendarBodyInfoByTeamId(Map queryMap);

    List<WorkLogDayListDto> queryWorkLogContent(WorkLogQueryDto logQueryForm2Dto);
}
