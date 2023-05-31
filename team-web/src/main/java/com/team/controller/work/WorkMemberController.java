package com.team.controller.work;


import cn.hutool.core.collection.CollectionUtil;
import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.form.work.WorkMemberChangeUpdateForm;
import com.team.form.work.WorkMemberEvaluateUpdateForm;
import com.team.form.work.WorkMemberQueryForm;
import com.team.vo.work.WorkMemberChangeQueryVo;
import com.team.vo.work.WorkMemberEvaluateQueryVo;
import com.team.work.dto.WorkEvaluateUpdateDto;
import com.team.work.dto.WorkMemberChangeUpdateDto;
import com.team.work.dto.WorkMemberListQueryDto;
import com.team.work.service.WorkMemberService;
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
 * 工单人员表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@RestController
@RequestMapping("/workMember")
@Api(value = "/workInfo", tags = "工单模块")
public class WorkMemberController extends BaseController {
    @Autowired
    private WorkMemberService workMemberService;
    /**
     * 查询人员变动列表
     */
    @ApiOperation(value = "工单人员变动列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public BaseResult<List<WorkMemberChangeQueryVo>> list(@RequestBody WorkMemberQueryForm form) {
        List<WorkMemberListQueryDto> dtoList = workMemberService.queryMemberListByWorkSn(form.getWorkNo());
        return success(converMemberChangeList2Vo(dtoList));
    }

    /**
     * 人员变动调整
     */
    @ApiOperation(value = "工单人员调整")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult<Boolean> update(@RequestBody WorkMemberChangeUpdateForm form) {
        WorkMemberChangeUpdateDto dto = converMemberChangeUpdateForm2Dto(form);
        Boolean result = workMemberService.updateChange(dto);
        return success(result);
    }

    /**
     * 拆分评价人员查询
     */
    @ApiOperation(value = "工单拆分评价")
    @RequestMapping(value = "/evaluateList", method = RequestMethod.POST)
    public BaseResult<List<WorkMemberEvaluateQueryVo>> evaluateList(@RequestBody WorkMemberQueryForm form) {
        List<WorkMemberListQueryDto> dtoList = workMemberService.queryMemberListByWorkSn(form.getWorkNo());
        return success(converMemberEvaluateList2Vo(dtoList));
    }

    /**
     * 拆分评价更新
     * @param form
     * @return
     */
    @ApiOperation(value = "工单拆分评价修改")
    @RequestMapping(value = "/evaluateUpdate", method = RequestMethod.POST)
    public BaseResult<Boolean> evaluateUpdate(@RequestBody WorkMemberEvaluateUpdateForm form) {
        WorkEvaluateUpdateDto dto = converMemberEvaluateUpdateForm2Dto(form);
        Boolean result = workMemberService.updateEvaluate(dto);
        return success(result);
    }

    public static WorkEvaluateUpdateDto converMemberEvaluateUpdateForm2Dto(WorkMemberEvaluateUpdateForm form) {
        if (form == null) {
            return null;
        }
        WorkEvaluateUpdateDto workEvaluateUpdateDto = new WorkEvaluateUpdateDto();
        workEvaluateUpdateDto.setWorkNo(form.getWorkNo());
        workEvaluateUpdateDto.setId(form.getId());
        workEvaluateUpdateDto.setActualStartTime(form.getActualStartTime());
        workEvaluateUpdateDto.setActualEndTime(form.getActualEndTime());
        workEvaluateUpdateDto.setWorkEvaluate(form.getWorkEvaluate());
        workEvaluateUpdateDto.setTotalIntegral(form.getTotalIntegral());
        workEvaluateUpdateDto.setAdjustIntegral(form.getAdjustIntegral());
        workEvaluateUpdateDto.setFinalIntegral(form.getFinalIntegral());
        return workEvaluateUpdateDto;
    }


    private List<WorkMemberEvaluateQueryVo> converMemberEvaluateList2Vo(List<WorkMemberListQueryDto> dtoList) {
        List<WorkMemberEvaluateQueryVo> queryList = new ArrayList<>();
        if(CollectionUtil.isEmpty(dtoList)){
            return queryList;
        }
        return dtoList.stream().map(i->converMemberEvaluate2Vo(i)).collect(Collectors.toList());
    }

    public static WorkMemberEvaluateQueryVo converMemberEvaluate2Vo(WorkMemberListQueryDto i) {
        if (i == null) {
            return null;
        }
        WorkMemberEvaluateQueryVo workMemberEvaluateQueryVo = new WorkMemberEvaluateQueryVo();
        workMemberEvaluateQueryVo.setWorkNo(i.getWorkNo());
        workMemberEvaluateQueryVo.setLeaderFlag(i.getLeaderFlag());
        workMemberEvaluateQueryVo.setId(i.getId());
        workMemberEvaluateQueryVo.setMemberId(i.getMemberId());
        workMemberEvaluateQueryVo.setMemberName(i.getMemberName());
        workMemberEvaluateQueryVo.setActualStartTime(i.getActualStartTime());
        workMemberEvaluateQueryVo.setActualEndTime(i.getActualEndTime());
        workMemberEvaluateQueryVo.setWorkEvaluate(i.getWorkEvaluate());
        workMemberEvaluateQueryVo.setTotalIntegral(i.getTotalIntegral());
        workMemberEvaluateQueryVo.setFinalIntegral(i.getFinalIntegral());
        workMemberEvaluateQueryVo.setAdjustIntegral(i.getAdjustIntegral());
        return workMemberEvaluateQueryVo;
    }

    public static WorkMemberChangeUpdateDto converMemberChangeUpdateForm2Dto(WorkMemberChangeUpdateForm form) {
        if (form == null) {
            return null;
        }
        WorkMemberChangeUpdateDto workMemberChangeUpdateDto = new WorkMemberChangeUpdateDto();
        workMemberChangeUpdateDto.setWorkNo(form.getWorkNo());
        workMemberChangeUpdateDto.setId(form.getId());
        workMemberChangeUpdateDto.setMemberId(form.getMemberId());
        workMemberChangeUpdateDto.setMemberName(form.getMemberName());
        workMemberChangeUpdateDto.setWorkDays(form.getWorkDays());
        return workMemberChangeUpdateDto;
    }

    private List<WorkMemberChangeQueryVo> converMemberChangeList2Vo(List<WorkMemberListQueryDto> dtoList) {
        List<WorkMemberChangeQueryVo> queryList = new ArrayList<>();
        if(CollectionUtil.isEmpty(dtoList)){
            return queryList;
        }
        return dtoList.stream().map(i->converMemberChangeQuery2Vo(i)).collect(Collectors.toList());
    }

    public static WorkMemberChangeQueryVo converMemberChangeQuery2Vo(WorkMemberListQueryDto i) {
        if (i == null) {
            return null;
        }
        WorkMemberChangeQueryVo workMemberQueryVo = new WorkMemberChangeQueryVo();
        workMemberQueryVo.setWorkNo(i.getWorkNo());
        workMemberQueryVo.setId(i.getId());
        workMemberQueryVo.setMemberId(i.getMemberId());
        workMemberQueryVo.setMemberName(i.getMemberName());
        workMemberQueryVo.setWorkDays(i.getWorkDays());
        workMemberQueryVo.setLeaderFlag(i.getLeaderFlag());
        return workMemberQueryVo;
    }

}

