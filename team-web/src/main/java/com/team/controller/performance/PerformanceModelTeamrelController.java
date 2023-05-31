package com.team.controller.performance;


import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.form.performance.model.BatchChoosePerformanceModelTeamForm;
import com.team.performance.dto.model.BatchChoosePerformanceModelTeamDto;
import com.team.performance.service.PerformanceModelTeamrelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 绩效评价模板部分班组关系表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Api(tags = "绩效评价模板与班组关系表管理")
@RestController
@RequestMapping("/performanceModelTeamrelVo")
public class PerformanceModelTeamrelController extends BaseController {

    @Autowired
    PerformanceModelTeamrelService performanceModelTeamrelServiceImpl;

    @ApiOperation(value = "批量选择适用班组")
    @RequestMapping(value = "/batchChooseTeam", method = RequestMethod.POST)
    public BaseResult<Integer> batchChooseTeam(@RequestBody BatchChoosePerformanceModelTeamForm form)
    {
        BatchChoosePerformanceModelTeamDto dto = converChooseTeamForm2Dto(form);
        Integer result = performanceModelTeamrelServiceImpl.batchChooseTeam(dto);
        return success(result);
    }

    public static BatchChoosePerformanceModelTeamDto converChooseTeamForm2Dto(BatchChoosePerformanceModelTeamForm form) {
        if (form == null) {
            return null;
        }
        form.verifyParam();
        BatchChoosePerformanceModelTeamDto batchChoosePerformanceModelTeamDto = new BatchChoosePerformanceModelTeamDto();
        batchChoosePerformanceModelTeamDto.setModelId(form.getModelId());
        batchChoosePerformanceModelTeamDto.setTeamCodes(form.getTeamCodes());
        batchChoosePerformanceModelTeamDto.setTeamNames(form.getTeamNames());
        batchChoosePerformanceModelTeamDto.setUpdatedBy(form.getUpdatedBy());
        return batchChoosePerformanceModelTeamDto;
    }
}

