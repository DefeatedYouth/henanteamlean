package com.team.controller.performance;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import com.team.common.constants.performance.PerforConstants;
import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.common.constants.result.PageInfo;
import com.team.common.exception.CheckException;
import com.team.form.performance.model.*;
import com.team.form.performance.monitorModel.*;
import com.team.performance.dto.model.*;
import com.team.performance.dto.monitorModel.*;
import com.team.performance.entity.PerformanceEvaluationModelVo;
import com.team.performance.service.PerformanceEvaModelByMonitorService;
import com.team.performance.service.PerformanceEvaluationModelService;
import com.team.vo.performance.PerformanceModelMonitorVo;
import com.team.vo.performance.PerformanceModelRuleMonitorVo;
import com.team.vo.performance.PerformanceModelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 班长-绩效评价模板表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Api(tags = "班长-绩效评价模板管理")
@RestController
@RequestMapping("/perforEvaModelMonitorVo")
public class PerformanceEvaModelByMonitorController extends BaseController {

    @Autowired
    private PerformanceEvaModelByMonitorService performanceEvaModelByMonitorServiceImpl;

    @ApiOperation(value = "分页查询班长绩效评价模板列表")
    @RequestMapping(value = "/queryMonitorPerforModelList", method = RequestMethod.POST)
    public BaseResult<PageInfo<PerformanceModelMonitorVo>> queryMonitorPerforModelList(@RequestBody QueryMonitorPerforModelListForm form)
    {
        QueryMonitorPerforModelListDto dto = converQueryListForm2Dto(form);
        Map<String,Object> map = performanceEvaModelByMonitorServiceImpl.queryMonitorPerforModelList(dto);
        // 最终返回列表
        List<PerformanceModelMonitorVo> modelList = Lists.newArrayList();
        if(Objects.nonNull(map) && Integer.valueOf(map.get("count").toString()) > 0){
            List<PerformanceEvaluationModelVo> list = (List<PerformanceEvaluationModelVo>)map.get("list");
            list.stream().forEach(e -> {
                PerformanceModelMonitorVo model = converQueryList2Vo(e);
                modelList.add(model);
            });
        }
        map.put("list",modelList);
        PageInfo<PerformanceModelMonitorVo> result = new PageInfo(map);
        return success(result);
    }

    // 返回结果处理
    public static PerformanceModelMonitorVo converQueryList2Vo(PerformanceEvaluationModelVo form) {
        PerformanceModelMonitorVo performanceModelMonitorVo = new PerformanceModelMonitorVo();
        performanceModelMonitorVo.setId(form.getId());
        performanceModelMonitorVo.setQuateName(form.getQuateName());
        performanceModelMonitorVo.setDetailRule(form.getDetailRule());
        performanceModelMonitorVo.setAssessmentCycle(form.getAssessmentCycle());
        performanceModelMonitorVo.setAssessor(form.getAssessor());
        performanceModelMonitorVo.setAssessmentObject(form.getAssessmentObject());
        performanceModelMonitorVo.setIntegral(form.getIntegral());
        performanceModelMonitorVo.setPublishState(form.getPublishState());
        performanceModelMonitorVo.setTeamName(form.getTeamName());
        performanceModelMonitorVo.setSource(form.getSource());
        return performanceModelMonitorVo;
    }

    // 分页查询绩效评价模板列表失败
    public static QueryMonitorPerforModelListDto converQueryListForm2Dto(QueryMonitorPerforModelListForm form) {
        if (form == null) {
            throw new CheckException("入参为空，分页查询绩效评价模板列表失败",new Exception("入参为空，分页查询绩效评价模板列表失败"));
        }
        form.verifyParam();
        QueryMonitorPerforModelListDto queryMonitorPerforModelListDto = new QueryMonitorPerforModelListDto();
        queryMonitorPerforModelListDto.setSource(form.getSource());
        queryMonitorPerforModelListDto.setQuateName(form.getQuateName());
        queryMonitorPerforModelListDto.setAssessmentCycle(form.getAssessmentCycle());
        queryMonitorPerforModelListDto.setPublishState(form.getPublishState());
        queryMonitorPerforModelListDto.setPageNum(form.getPageNum());
        queryMonitorPerforModelListDto.setPageSize(form.getPageSize());
        queryMonitorPerforModelListDto.setProductorId(form.getProductorId());
        return queryMonitorPerforModelListDto;
    }

    @ApiOperation(value = "添加班长绩效评价模板")
    @RequestMapping(value = "/addMonitorPerforModel", method = RequestMethod.POST)
    public BaseResult<Integer> addMonitorPerforModel(@RequestBody AddMonitorPerforModelForm form)
    {
        AddMonitorPerforModelDto dto = converAddForm2Dto(form);
        Integer result = performanceEvaModelByMonitorServiceImpl.addMonitorPerforModel(dto);
        return success(result);
    }

    // 添加绩效评价模板
    public static AddMonitorPerforModelDto converAddForm2Dto(AddMonitorPerforModelForm form)
    {
        if (form == null) {
            throw new CheckException("入参为空，添加绩效评价模板失败",new Exception("入参为空，添加绩效评价模板失败"));
        }
        form.verifyParam();
        AddMonitorPerforModelDto addMonitorPerforModelDto = new AddMonitorPerforModelDto();
        addMonitorPerforModelDto.setQuateName(form.getQuateName());
        addMonitorPerforModelDto.setDetailRule(form.getDetailRule());
        addMonitorPerforModelDto.setAssessmentCycle(form.getAssessmentCycle());
        addMonitorPerforModelDto.setAssessor(form.getAssessor());
        addMonitorPerforModelDto.setAssessmentObject(form.getAssessmentObject());
        addMonitorPerforModelDto.setIntegral(form.getIntegral());
        addMonitorPerforModelDto.setProductorId(form.getProductorId());
        addMonitorPerforModelDto.setCreatedBy(form.getCreatedBy());
        return addMonitorPerforModelDto;
    }

    @ApiOperation(value = "编辑班长绩效评价模板")
    @RequestMapping(value = "/editMonitorPerforModel", method = RequestMethod.POST)
    public BaseResult<Integer> editMonitorPerforModel(@RequestBody EditMonitorPerforModelForm form)
    {
        EditMonitorPerforModelDto dto = converEditForm2Dto(form);
        Integer result = performanceEvaModelByMonitorServiceImpl.editMonitorPerforModel(dto);
        return success(result);
    }

    // 编辑绩效评价模板
    public static EditMonitorPerforModelDto converEditForm2Dto(EditMonitorPerforModelForm form) {
        if (form == null) {
            throw new CheckException("入参为空，编辑绩效评价模板失败",new Exception("入参为空，编辑绩效评价模板失败"));
        }
        // 校验
        form.verifyParam();
        EditMonitorPerforModelDto editMonitorPerforModelDto = new EditMonitorPerforModelDto();
        editMonitorPerforModelDto.setId(form.getId());
        editMonitorPerforModelDto.setQuateName(form.getQuateName());
        editMonitorPerforModelDto.setDetailRule(form.getDetailRule());
        editMonitorPerforModelDto.setAssessmentCycle(form.getAssessmentCycle());
        editMonitorPerforModelDto.setAssessor(form.getAssessor());
        editMonitorPerforModelDto.setAssessmentObject(form.getAssessmentObject());
        editMonitorPerforModelDto.setIntegral(form.getIntegral());
        editMonitorPerforModelDto.setUpdatedBy(form.getUpdatedBy());
        return editMonitorPerforModelDto;
    }

    @ApiOperation(value = "删除班长绩效评价模板")
    @RequestMapping(value = "/deleteMonitorPerforModel", method = RequestMethod.POST)
    public BaseResult<Integer> deleteMonitorPerforModel(@RequestBody DeleteMonitorPerforModelForm form)
    {
        DeleteMonitorPerforModelDto dto = converDeleteForm2Dto(form);
        Integer result = performanceEvaModelByMonitorServiceImpl.deleteMonitorPerforModel(dto);
        return success(result);
    }

    // 删除绩效评价模板
    public static DeleteMonitorPerforModelDto converDeleteForm2Dto(DeleteMonitorPerforModelForm form) {
        if (form == null) {
            return null;
        }
        form.verifyParam();
        DeleteMonitorPerforModelDto deleteMonitorPerforModelDto = new DeleteMonitorPerforModelDto();
        deleteMonitorPerforModelDto.setId(form.getId());
        deleteMonitorPerforModelDto.setUpdatedBy(form.getUpdatedBy());
        return deleteMonitorPerforModelDto;
    }

    @ApiOperation(value = "发布班长绩效评价模板")
    @RequestMapping(value = "/publishMonitorPerforModel", method = RequestMethod.POST)
    public BaseResult<Integer> publishMonitorPerforModel(@RequestBody PublishMonitorPerforModelForm form)
    {
        PublishMonitorPerforModelDto dto = converPublishForm2Dto(form);
        Integer result = performanceEvaModelByMonitorServiceImpl.publishMonitorPerforModel(dto);
        return success(result);
    }

    // 发布绩效评价模板
    public static PublishMonitorPerforModelDto converPublishForm2Dto(PublishMonitorPerforModelForm form) {
        if (form == null) {
            return null;
        }
        form.verifyParam();
        PublishMonitorPerforModelDto monitorPerforModelDto = new PublishMonitorPerforModelDto();
        // 流水号转成List<String>
        List<String> idsList = Arrays.asList(form.getIds().split(","));
        // List<String>转成List<Integer>
        List<Integer> newList = Lists.newArrayList();
        idsList.stream().forEach(e -> {
            newList.add(Integer.valueOf(e));
        });
        monitorPerforModelDto.setIds(newList);
        monitorPerforModelDto.setUpdatedBy(form.getUpdatedBy());
        return monitorPerforModelDto;
    }

    @ApiOperation(value = "取消发布班长绩效评价模板")
    @RequestMapping(value = "/cancelcPublishMonitorPerforModel", method = RequestMethod.POST)
    public BaseResult<Integer> cancelcPublishMonitorPerforModel(@RequestBody CancelPublishMonitorPerforModelForm form)
    {
        CancelPublishMonitorPerforModelDto dto = converCancelPublishForm2Dto(form);
        Integer result = performanceEvaModelByMonitorServiceImpl.cancelcPublishMonitorPerforModel(dto);
        return success(result);
    }

    // 取消发布绩效评价模板
    public static CancelPublishMonitorPerforModelDto converCancelPublishForm2Dto(CancelPublishMonitorPerforModelForm form) {
        if (form == null) {
            return null;
        }
        form.verifyParam();
        CancelPublishMonitorPerforModelDto cancelPublishMonitorPerforModelDto = new CancelPublishMonitorPerforModelDto();
        // 流水号转成List<String>
        List<String> idsList = Arrays.asList(form.getIds().split(","));
        // List<String>转成List<Integer>
        List<Integer> newList = Lists.newArrayList();
        idsList.stream().forEach(e -> {
            newList.add(Integer.valueOf(e));
        });
        cancelPublishMonitorPerforModelDto.setIds(newList);
        cancelPublishMonitorPerforModelDto.setUpdatedBy(form.getUpdatedBy());
        return cancelPublishMonitorPerforModelDto;
    }

    @ApiOperation(value = "班长绩效评价模板标准查看")
    @RequestMapping(value = "/queryMonitorPerforModelRuleList", method = RequestMethod.POST)
    public BaseResult<List<PerformanceModelRuleMonitorVo>> queryMonitorPerforModelRuleList(@RequestBody QueryMonitorPerforModelRuleListForm form)
    {
        // 最终返回列表
        List<PerformanceModelRuleMonitorVo> modelList = Lists.newArrayList();
        QueryMonitorPerforModelRuleListDto dto = converRuleForm2Dto(form);
        // 标准查询列表
        List<PerformanceEvaluationModelVo> list = performanceEvaModelByMonitorServiceImpl.queryMonitorPerforModelRuleList(dto);
        if(CollectionUtil.isNotEmpty(list))
        {
            list.stream().forEach(e -> {
                PerformanceModelRuleMonitorVo model = converQueryRuleList2Vo(e);
                modelList.add(model);
            });
        }
        return success(modelList);
    }

    // 班长绩效评价模板标准查看
    public static QueryMonitorPerforModelRuleListDto converRuleForm2Dto(QueryMonitorPerforModelRuleListForm form) {
        if (form == null) {
            return null;
        }
        QueryMonitorPerforModelRuleListDto queryMonitorPerforModelRuleListDto = new QueryMonitorPerforModelRuleListDto();
        queryMonitorPerforModelRuleListDto.setProductorId(form.getProductorId());
        return queryMonitorPerforModelRuleListDto;
    }

    // 班长绩效评价模板标准查看
    public static PerformanceModelRuleMonitorVo converQueryRuleList2Vo(PerformanceEvaluationModelVo e) {
        if (e == null) {
            return null;
        }
        PerformanceModelRuleMonitorVo performanceModelRuleMonitorVo = new PerformanceModelRuleMonitorVo();
        performanceModelRuleMonitorVo.setQuateName(e.getQuateName());
        performanceModelRuleMonitorVo.setDetailRule(e.getDetailRule());
        performanceModelRuleMonitorVo.setAssessmentCycle(e.getAssessmentCycle());
        performanceModelRuleMonitorVo.setAssessor(e.getAssessor());
        performanceModelRuleMonitorVo.setAssessmentObject(e.getAssessmentObject());
        performanceModelRuleMonitorVo.setIntegral(e.getIntegral());
        performanceModelRuleMonitorVo.setPublishState(e.getPublishState());
        return performanceModelRuleMonitorVo;
    }
}

