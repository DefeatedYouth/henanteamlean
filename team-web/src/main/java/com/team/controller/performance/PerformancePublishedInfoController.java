package com.team.controller.performance;


import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.team.common.constants.result.BaseResult;
import com.team.form.performance.publish.QueryHistoryPerforPublishListForm;
import com.team.form.performance.publish.QueryPerforPublishModelListForm;
import com.team.performance.dto.publish.QueryHistoryPerforPublishListDto;
import com.team.performance.dto.publish.QueryPerforPublishModelListDto;
import com.team.performance.entity.PerformancePublishedInfoVo;
import com.team.performance.service.PerformancePublishedInfoService;
import com.team.vo.performance.HistoryPerforModelMainVo;
import com.team.vo.performance.HistoryPerforModelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.team.common.constants.result.BaseController.success;

/**
 * <p>
 * 绩效评价已发布表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@RestController
@Api(tags = "已发布绩效评价模板管理")
@RequestMapping("/performancePublishedInfoVo")
public class PerformancePublishedInfoController {

    @Autowired
    PerformancePublishedInfoService performancePublishedInfoServiceImpl;

    // 查询历史发布记录
    @ApiOperation(value = "查询历史绩效发布记录列表")
    @RequestMapping(value = "/queryHistoryPerforPublishList", method = RequestMethod.POST)
    public BaseResult<List<HistoryPerforModelMainVo>> queryHistoryPerforPublishList(@RequestBody QueryHistoryPerforPublishListForm form)
    {
        QueryHistoryPerforPublishListDto dto = converQueryListForm2Dto(form);
        List<PerformancePublishedInfoVo> list = performancePublishedInfoServiceImpl.queryHistoryPerforPublishList(dto);
        // 最终返回列表
        List<HistoryPerforModelMainVo> historyList = Lists.newArrayList();

        list.stream().forEach(e -> {
            HistoryPerforModelMainVo model = converQueryList2Vo(e);
            historyList.add(model);
        });
        return success(historyList);
    }

    // 查询历史绩效发布记录列表
    public static HistoryPerforModelMainVo converQueryList2Vo(PerformancePublishedInfoVo e) {
        if (e == null) {
            return null;
        }
        HistoryPerforModelMainVo historyPerforModelMainVo = new HistoryPerforModelMainVo();
        historyPerforModelMainVo.setQuateName(e.getQuateName());
        // 细则
        List<HistoryPerforModelVo> detailList = Lists.newArrayList();
        if(CollectionUtil.isNotEmpty(e.getDetailList())){
            e.getDetailList().stream().forEach(m -> {
                HistoryPerforModelVo model = converQueryHistoryListForm2Dto(m);
                detailList.add(model);
            });
        }
        historyPerforModelMainVo.setDetailList(detailList);
        return historyPerforModelMainVo;
    }

    // 查询历史绩效发布记录列表
    public static HistoryPerforModelVo converQueryHistoryListForm2Dto(PerformancePublishedInfoVo m) {
        if (m == null) {
            return null;
        }
        HistoryPerforModelVo historyPerforModelVo = new HistoryPerforModelVo();
        historyPerforModelVo.setId(m.getId());
        historyPerforModelVo.setDetailRule(m.getDetailRule());
        historyPerforModelVo.setAssessmentCycle(m.getAssessmentCycle());
        historyPerforModelVo.setAssessor(m.getAssessor());
        historyPerforModelVo.setAssessmentObject(m.getAssessmentObject());
        historyPerforModelVo.setIntegral(m.getIntegral());
        return historyPerforModelVo;
    }

    // 查询历史绩效发布记录列表
    public static QueryHistoryPerforPublishListDto converQueryListForm2Dto(QueryHistoryPerforPublishListForm form) {
        if (form == null) {
            return null;
        }
        form.verifyParam();
        QueryHistoryPerforPublishListDto queryHistoryPerforPublishListDto = new QueryHistoryPerforPublishListDto();
        queryHistoryPerforPublishListDto.setProductorId(form.getProductorId());
        queryHistoryPerforPublishListDto.setYear(form.getYear());
        queryHistoryPerforPublishListDto.setMonth(form.getMonth());
        queryHistoryPerforPublishListDto.setQuarter(form.getQuarter());
        return queryHistoryPerforPublishListDto;
    }

    // 班员绩效评价标准查询
    @ApiOperation(value = "查询班员绩效评价标准")
    @RequestMapping(value = "/queryPerforPublishModelList", method = RequestMethod.POST)
    public BaseResult<List<HistoryPerforModelMainVo>> queryPerforPublishModelList(@RequestBody QueryPerforPublishModelListForm form)
    {
        QueryPerforPublishModelListDto dto = converQueryModelListForm2Dto(form);
        List<PerformancePublishedInfoVo> list = performancePublishedInfoServiceImpl.queryPerforPublishModelList(dto);
        // 最终返回列表
        List<HistoryPerforModelMainVo> historyList = Lists.newArrayList();

        list.stream().forEach(e -> {
            HistoryPerforModelMainVo model = converQueryList2Vo(e);
            historyList.add(model);
        });
        return success(historyList);
    }

    // 查询班员绩效评价标准
    public static QueryPerforPublishModelListDto converQueryModelListForm2Dto(QueryPerforPublishModelListForm form) {
        if (form == null) {
            return null;
        }
        QueryPerforPublishModelListDto queryPerforPublishModelListDto = new QueryPerforPublishModelListDto();
        queryPerforPublishModelListDto.setProductorId(form.getProductorId());
        queryPerforPublishModelListDto.setYear(form.getYear());
        queryPerforPublishModelListDto.setMonth(form.getMonth());
        queryPerforPublishModelListDto.setQuarter(form.getQuarter());
        return queryPerforPublishModelListDto;
    }
}

