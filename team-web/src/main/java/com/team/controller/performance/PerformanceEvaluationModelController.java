package com.team.controller.performance;

import com.google.common.collect.Lists;
import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.common.constants.result.PageInfo;
import com.team.common.exception.CheckException;
import com.team.form.performance.model.*;
import com.team.performance.dto.model.*;
import com.team.performance.entity.PerformanceEvaluationModelVo;
import com.team.performance.service.PerformanceEvaluationModelService;
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
 * 绩效评价模板表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Api(tags = "工区专责-绩效评价模板管理")
@RestController
@RequestMapping("/performanceEvaluationModelVo")
public class PerformanceEvaluationModelController extends BaseController {

    @Autowired
    private PerformanceEvaluationModelService performanceEvaluationModelServiceImpl;

    @ApiOperation(value = "分页查询绩效评价模板列表")
    @RequestMapping(value = "/queryPerformanceModelList", method = RequestMethod.POST)
    public BaseResult<PageInfo<PerformanceModelVo>> queryPerformanceModelList(@RequestBody QueryPerformanceModelListForm form)
    {
        QueryPerformanceModelListDto dto = converQueryListForm2Dto(form);
        Map<String,Object> map = performanceEvaluationModelServiceImpl.queryPerformanceModelList(dto);
        // 最终返回列表
        List<PerformanceModelVo> modelList = Lists.newArrayList();
        if(Objects.nonNull(map) && Integer.valueOf(map.get("count").toString()) > 0){
            List<PerformanceEvaluationModelVo> list = (List<PerformanceEvaluationModelVo>)map.get("list");
            list.stream().forEach(e -> {
                PerformanceModelVo model = converQueryList2Vo(e);
                modelList.add(model);
            });
        }
        map.put("list",modelList);
        PageInfo<PerformanceModelVo> result = new PageInfo(map);
        return success(result);
    }

    // 返回结果处理
    public static PerformanceModelVo converQueryList2Vo(PerformanceEvaluationModelVo form) {
        PerformanceModelVo performanceModelVo = new PerformanceModelVo();
        performanceModelVo.setId(form.getId());
        performanceModelVo.setIsHavTeam(form.getIsHavTeam());
        performanceModelVo.setTeamName(form.getTeamName());
        performanceModelVo.setQuateName(form.getQuateName());
        performanceModelVo.setDetailRule(form.getDetailRule());
        performanceModelVo.setAssessmentCycle(form.getAssessmentCycle());
        performanceModelVo.setAssessor(form.getAssessor());
        performanceModelVo.setAssessmentObject(form.getAssessmentObject());
        performanceModelVo.setIntegral(form.getIntegral());
        performanceModelVo.setCompanyName(form.getCompanyName());
        performanceModelVo.setCreatedBy(form.getCreatedBy());
        performanceModelVo.setPublishTime(form.getPublishTime());
        performanceModelVo.setPublishState(form.getPublishState());
        return performanceModelVo;
    }

    // 分页查询绩效评价模板列表失败
    public static QueryPerformanceModelListDto converQueryListForm2Dto(QueryPerformanceModelListForm form) {
        if (form == null) {
            throw new CheckException("入参为空，分页查询绩效评价模板列表失败",new Exception("入参为空，分页查询绩效评价模板列表失败"));
        }
        form.verifyParam();
        QueryPerformanceModelListDto queryPerformanceModelListDto = new QueryPerformanceModelListDto();
        queryPerformanceModelListDto.setQuateName(form.getQuateName());
        queryPerformanceModelListDto.setAssessmentCycle(form.getAssessmentCycle());
        queryPerformanceModelListDto.setTeamCode(form.getTeamCode());
        queryPerformanceModelListDto.setPublishState(form.getPublishState());
        queryPerformanceModelListDto.setPageNum(form.getPageNum());
        queryPerformanceModelListDto.setPageSize(form.getPageSize());
        queryPerformanceModelListDto.setProductorId(form.getProductorId());
        return queryPerformanceModelListDto;
    }

    @ApiOperation(value = "添加绩效评价模板")
    @RequestMapping(value = "/addPerformanceEvaluationModel", method = RequestMethod.POST)
    public BaseResult<Integer> addPerformanceEvaluationModel(@RequestBody AddPerformanceModelForm form)
    {
        AddPerformanceModelDto dto = converAddForm2Dto(form);
        Integer result = performanceEvaluationModelServiceImpl.addPerformanceEvaluationModel(dto);
        return success(result);
    }

    public static AddPerformanceModelDto converAddForm2Dto(AddPerformanceModelForm form) {
        if (form == null) {
            throw new CheckException("入参为空，添加绩效评价模板失败",new Exception("入参为空，添加绩效评价模板失败"));
        }
        form.verifyParam();
        AddPerformanceModelDto addPerformanceModelDto = new AddPerformanceModelDto();
        addPerformanceModelDto.setQuateName(form.getQuateName());
        addPerformanceModelDto.setDetailRule(form.getDetailRule());
        addPerformanceModelDto.setAssessmentCycle(form.getAssessmentCycle());
        addPerformanceModelDto.setAssessor(form.getAssessor());
        addPerformanceModelDto.setAssessmentObject(form.getAssessmentObject());
        addPerformanceModelDto.setIntegral(form.getIntegral());
        addPerformanceModelDto.setProductorId(form.getProductorId());
        addPerformanceModelDto.setCreatedBy(form.getCreatedBy());
        return addPerformanceModelDto;
    }

    @ApiOperation(value = "编辑绩效评价模板")
    @RequestMapping(value = "/editPerformanceEvaluationModel", method = RequestMethod.POST)
    public BaseResult<Integer> editPerformanceEvaluationModel(@RequestBody EditPerformanceModelForm form)
    {
        EditPerformanceModelDto dto = converEditForm2Dto(form);
        Integer result = performanceEvaluationModelServiceImpl.editPerformanceEvaluationModel(dto);
        return success(result);
    }

    public static EditPerformanceModelDto converEditForm2Dto(EditPerformanceModelForm form) {
        if (form == null) {
            throw new CheckException("入参为空，编辑绩效评价模板失败",new Exception("入参为空，编辑绩效评价模板失败"));
        }
        // 校验
        form.verifyParam();
        EditPerformanceModelDto editPerformanceModelDto = new EditPerformanceModelDto();
        editPerformanceModelDto.setId(form.getId());
        editPerformanceModelDto.setQuateName(form.getQuateName());
        editPerformanceModelDto.setDetailRule(form.getDetailRule());
        editPerformanceModelDto.setAssessmentCycle(form.getAssessmentCycle());
        editPerformanceModelDto.setAssessor(form.getAssessor());
        editPerformanceModelDto.setAssessmentObject(form.getAssessmentObject());
        editPerformanceModelDto.setIntegral(form.getIntegral());
        editPerformanceModelDto.setUpdatedBy(form.getUpdatedBy());
        return editPerformanceModelDto;
    }

    @ApiOperation(value = "删除绩效评价模板")
    @RequestMapping(value = "/deletePerformanceEvaluationModel", method = RequestMethod.POST)
    public BaseResult<Integer> deletePerformanceEvaluationModel(@RequestBody DeletePerformanceModelForm form)
    {
        DeletePerformanceModelDto dto = converDeleteForm2Dto(form);
        Integer result = performanceEvaluationModelServiceImpl.deletePerformanceEvaluationModel(dto);
        return success(result);
    }

    public static DeletePerformanceModelDto converDeleteForm2Dto(DeletePerformanceModelForm form) {
        if (form == null) {
            return null;
        }
        form.verifyParam();
        DeletePerformanceModelDto deletePerformanceModelDto = new DeletePerformanceModelDto();
        deletePerformanceModelDto.setId(form.getId());
        deletePerformanceModelDto.setUpdatedBy(form.getUpdatedBy());
        return deletePerformanceModelDto;
    }

    @ApiOperation(value = "发布绩效评价模板")
    @RequestMapping(value = "/publishPerformanceEvaluationModel", method = RequestMethod.POST)
    public BaseResult<Integer> publishPerformanceEvaluationModel(@RequestBody PublishPerformanceModelForm form)
    {
        PublishPerformanceModelDto dto = converPublishForm2Dto(form);
        Integer result = performanceEvaluationModelServiceImpl.publishPerformanceEvaluationModel(dto);
        return success(result);
    }

    // 发布绩效评价模板
    public static PublishPerformanceModelDto converPublishForm2Dto(PublishPerformanceModelForm form) {
        if (form == null) {
            return null;
        }
        form.verifyParam();
        PublishPerformanceModelDto publishPerformanceModelDto = new PublishPerformanceModelDto();
        // 流水号转成List<String>
        List<String> idsList = Arrays.asList(form.getIds().split(","));
        // List<String>转成List<Integer>
        List<Integer> newList = Lists.newArrayList();
        idsList.stream().forEach(e -> {
            newList.add(Integer.valueOf(e));
        });
        publishPerformanceModelDto.setIds(newList);
        publishPerformanceModelDto.setUpdatedBy(form.getUpdatedBy());
        return publishPerformanceModelDto;
    }

    @ApiOperation(value = "取消发布绩效评价模板")
    @RequestMapping(value = "/cancelcPublishPerformanceModel", method = RequestMethod.POST)
    public BaseResult<Integer> cancelcPublishPerformanceModel(@RequestBody CancelPublishPerformanceModelForm form)
    {
        CancelPublishPerformanceModelDto dto = converCancelPublishForm2Dto(form);
        Integer result = performanceEvaluationModelServiceImpl.cancelcPublishPerformanceModel(dto);
        return success(result);
    }

    // 取消发布绩效评价模板
    public static CancelPublishPerformanceModelDto converCancelPublishForm2Dto(CancelPublishPerformanceModelForm form) {
        if (form == null) {
            return null;
        }
        form.verifyParam();
        CancelPublishPerformanceModelDto cancelPublishPerformanceModelDto = new CancelPublishPerformanceModelDto();
        // 流水号转成List<String>
        List<String> idsList = Arrays.asList(form.getIds().split(","));
        // List<String>转成List<Integer>
        List<Integer> newList = Lists.newArrayList();
        idsList.stream().forEach(e -> {
            newList.add(Integer.valueOf(e));
        });
        cancelPublishPerformanceModelDto.setIds(newList);
        cancelPublishPerformanceModelDto.setUpdatedBy(form.getUpdatedBy());
        return cancelPublishPerformanceModelDto;
    }
}

