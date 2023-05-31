package com.team.controller.work;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.form.work.WorkCalendarForm;
import com.team.form.work.WorkCalendarLogQueryForm;
import com.team.form.work.WorkCalendarVacateCreateForm;
import com.team.vo.work.WorkCalendarCountDetailVo;
import com.team.vo.work.WorkCalendarLogReportListVo;
import com.team.vo.work.WorkCalendarVo;
import com.team.work.dto.*;
import com.team.work.service.WorkMemberCheckingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 工单员工考勤表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@RestController
@RequestMapping("/workMemberChecking")
@Api(value = "/workInfo", tags = "工单模块")
public class WorkMemberCheckingController extends BaseController {
    @Autowired
    private WorkMemberCheckingService workMemberCheckingService;

    /**
     * 查询日历
     * @param dto
     * @return
     */
    @ApiOperation(value = "工单日历")
    @RequestMapping(value = "/queryCalendar", method = RequestMethod.POST)
    public BaseResult<List<WorkCalendarVo>> queryCalendar(@RequestBody WorkCalendarForm form) {
        List<WorkCalendarDto> dto = workMemberCheckingService
                .queryCalendar(form.getMonth(),form.getTeamId());
        return success(converCalendarDtos2Vo(dto));
    }
    /**
     * 查询日历详情
     */
    @ApiOperation(value = "工单日历右侧详情")
    @RequestMapping(value = "/queryCalendarDetail", method = RequestMethod.POST)
    public BaseResult<WorkCalendarCountDetailVo> queryCountDetail(
            @RequestBody WorkCalendarForm form) {
        WorkMemberCheckingCountDetailDto dto = workMemberCheckingService
                .queryCountDetail(form.getMonth(),form.getTeamId(),form.getMemberId());
        return success(converCountDetailDto2Vo(dto));
    }

    /**
     * 查询日志列表
     * @param dto
     * @return
     */
    @ApiOperation(value = "工单日志列表")
    @RequestMapping(value = "/queryWorkLogList", method = RequestMethod.POST)
    public BaseResult<List<WorkCalendarLogReportListVo>> queryWorkLogList(
            @RequestBody WorkCalendarLogQueryForm form) {
        List<WorkLogResultDto> result = workMemberCheckingService.queryWorkLogList(converLogQueryForm2Dto(form));
        return success(converWorkLogList2Vo(result));
    }

   /**
     * 新增假期
     */
   @ApiOperation(value = "工单新增假期")
    @RequestMapping(value = "/createVacate", method = RequestMethod.POST)
    public BaseResult<Boolean> createVacate(
            @RequestBody WorkCalendarVacateCreateForm form) {
        Boolean result = workMemberCheckingService.createVacate(converCreateForm(form));
        return success(result);
    }

    public static WorkVacateCreateDto converCreateForm(WorkCalendarVacateCreateForm form) {
        if (form == null) {
            return null;
        }
        WorkVacateCreateDto workVacateCreateDto = new WorkVacateCreateDto();
        workVacateCreateDto.setMemberId(form.getMemberId());
        workVacateCreateDto.setMemberName(form.getMemberName());
        workVacateCreateDto.setLeaveType(form.getLeaveType());
        workVacateCreateDto.setStartTime(form.getStartTime());
        workVacateCreateDto.setEndTime(form.getEndTime());
        workVacateCreateDto.setRemark(form.getRemark());
        return workVacateCreateDto;
    }
    private List<WorkCalendarLogReportListVo> converWorkLogList2Vo(List<WorkLogResultDto> result) {
        List<WorkCalendarLogReportListVo> voList = new ArrayList<>();
        if(CollectionUtil.isEmpty(result)){
            return voList;
        }
        voList = result.stream().map(
                i->converWorkLog2Vo(i)
        ).collect(Collectors.toList());
        return voList;
    }
    public static WorkCalendarLogReportListVo converWorkLog2Vo(WorkLogResultDto i) {
        if (i == null) {
            return null;
        }
        WorkCalendarLogReportListVo workCalendarLogReportListVo = new WorkCalendarLogReportListVo();
        workCalendarLogReportListVo.setTeamName(i.getTeamName());
        workCalendarLogReportListVo.setMemberId(i.getMemberId());
        workCalendarLogReportListVo.setMemberName(i.getMemberName());
        workCalendarLogReportListVo.setErpNo(i.getErpNo());
        workCalendarLogReportListVo.setRemark(i.getRemark());
        if(CollectionUtil.isNotEmpty(i.getDayInfos())){
            workCalendarLogReportListVo.setDayInfos(i.getDayInfos()
                    .stream()
                    .map(d->{
                        WorkCalendarLogReportListVo.DayInfo dayInfo = new WorkCalendarLogReportListVo.DayInfo();
                        dayInfo.setContent(StrUtil.removeAll(d.getContent(),'{','}'));
                        dayInfo.setDayAt(d.getDayAt());
                        return dayInfo;
                    })
                    .collect(Collectors.toList()));
        }
        return workCalendarLogReportListVo;
    }
    public static WorkLogQueryDto converLogQueryForm2Dto(WorkCalendarLogQueryForm form) {
        if (form == null) {
            return null;
        }
        WorkLogQueryDto workLogQueryDto = new WorkLogQueryDto();
        workLogQueryDto.setStartTime(Integer.valueOf(form.getStartTime()));
        workLogQueryDto.setEndTime(Integer.valueOf(form.getEndTime()));
        return workLogQueryDto;
    }
    private List<WorkCalendarVo> converCalendarDtos2Vo(List<WorkCalendarDto> dto) {
        List<WorkCalendarVo> voList = new ArrayList<>();
        if(CollectionUtil.isEmpty(dto)){
            return voList;
        }
        for (WorkCalendarDto workCalendarDto : dto) {
            voList.add(converCalendarDto2Vo(workCalendarDto));
        }
        return voList;
    }
    public static WorkCalendarVo converCalendarDto2Vo(WorkCalendarDto workCalendarDto) {
        if (workCalendarDto == null) {
            return null;
        }
        WorkCalendarVo workCalendarVo = new WorkCalendarVo();
        workCalendarVo.setDayAt(workCalendarDto.getDayAt());
        workCalendarVo.setOnWorkCount(workCalendarDto.getOnWorkCount()==null?0:workCalendarDto.getOnWorkCount());
        workCalendarVo.setOnTripCount(workCalendarDto.getOnTripCount()==null?0:workCalendarDto.getOnTripCount());
        workCalendarVo.setOnLeaveCount(workCalendarDto.getOnLeaveCount()==null?0:workCalendarDto.getOnLeaveCount());
        if(CollectionUtil.isNotEmpty(workCalendarDto.getWorkPlans())) {
            workCalendarVo.setWorkPlans(workCalendarDto.getWorkPlans()
                    .stream().map(i-> {
                        WorkCalendarVo.WorkPlan plan = new WorkCalendarVo.WorkPlan();
                        plan.setWorkNum(i.getWorkNum());
                        plan.setWorkType(i.getWorkType());
                        plan.setWorkMember(StrUtil.removeAll(i.getWorkMember(),'{','}'));
                        return plan;
                    }).collect(Collectors.toList()));
        }
        return workCalendarVo;
    }
    public static WorkCalendarCountDetailVo converCountDetailDto2Vo(WorkMemberCheckingCountDetailDto dto) {
        if (dto == null) {
            return null;
        }
        WorkCalendarCountDetailVo workCalendarCountDetailVo = new WorkCalendarCountDetailVo();
        workCalendarCountDetailVo.setMemberNum(dto.getMemberNum());
        workCalendarCountDetailVo.setWorkNum(dto.getWorkNum());
        if(CollectionUtil.isNotEmpty(dto.getPersonPlans())){
            workCalendarCountDetailVo.setPersonPlans(dto.getPersonPlans()
                    .stream().map(i -> converPersonDto2Vo(i))
                    .collect(Collectors.toList()));
        }
        if(CollectionUtil.isNotEmpty(dto.getWorkPlans())) {
            workCalendarCountDetailVo.setWorkPlans(dto.getWorkPlans()
                    .stream().map(i -> converWorkPlansDto2Vo(i))
                    .collect(Collectors.toList()));
        }
        return workCalendarCountDetailVo;
    }
    private static WorkCalendarCountDetailVo.WorkPlan converWorkPlansDto2Vo(WorkPlanDto i) {
        WorkCalendarCountDetailVo.WorkPlan plan = new WorkCalendarCountDetailVo.WorkPlan();
        plan.setWorkNum(i.getWorkNum());
        plan.setWorkType(i.getWorkType());
        return plan;
    }
    private static WorkCalendarCountDetailVo.PersonPlan converPersonDto2Vo(PersonPlanDto i) {
        WorkCalendarCountDetailVo.PersonPlan personPlan = new WorkCalendarCountDetailVo.PersonPlan();
        personPlan.setType(i.getType());
        personPlan.setPersonCount(i.getPersonCount());
        personPlan.setDayCount(i.getDayCount());
        return personPlan;
    }

}

