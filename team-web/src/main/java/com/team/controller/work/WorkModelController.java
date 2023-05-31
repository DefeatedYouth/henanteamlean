package com.team.controller.work;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.form.work.WorkModelInfoCreateForm;
import com.team.form.work.WorkModelInfoDeleteForm;
import com.team.form.work.WorkModelInfoUpdateForm;
import com.team.form.work.WorkModelListPageForm;
import com.team.vo.work.WorkModelListPageVo;
import com.team.work.entity.WorkModelVo;
import com.team.work.service.WorkModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 工单模板表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@RestController
@RequestMapping("/workModel")
@Api(value = "/workInfo", tags = "工单模块")
public class WorkModelController extends BaseController {

    @Autowired
    private WorkModelService workModelService;

    /**
     * 查询模板列表
     * @return
     */
    @ApiOperation(value = "工单模板分页列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public BaseResult<List<WorkModelListPageVo>> list(@RequestBody WorkModelListPageForm form) {
        Page<WorkModelVo> resultDtoList = workModelService.queryList(form.getPageNum(),form.getPageSize());
        List<WorkModelListPageVo> resultList = converModelEntityList2Vo(resultDtoList.getRecords());
        BaseResult<List<WorkModelListPageVo>> success = success(resultList);
        success.setPages(resultDtoList.getTotal());
        return success;
    }
    /**
     * 新增模板
     * @param
     * @return
     */
    @ApiOperation(value = "工单模板新增")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public BaseResult<Boolean> create(@RequestBody WorkModelInfoCreateForm form) {
        WorkModelVo entity = converModelCreate2Entity(form);
        Boolean result = workModelService.saveWorkModel(entity);
        return success(result);
    }
    /**
     * 修改模板
     * @param
     * @return
     */
    @ApiOperation(value = "工单模板修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult<Boolean> update(@RequestBody WorkModelInfoUpdateForm form) {
        WorkModelVo entity = converModelUpdate2Entity(form);
        Boolean result = workModelService.updateWorkModel(entity);
        return success(result);
    }

    /**
     * 删除模板
     * @param form
     * @return
     */
    @ApiOperation(value = "工单模板删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult<Boolean> delete(@RequestBody WorkModelInfoDeleteForm form) {
        WorkModelVo entity = new WorkModelVo();
        entity.setId(form.getId());
        Boolean result = workModelService.deleteWorkModel(entity);
        return success(result);
    }

    public static WorkModelVo converModelUpdate2Entity(WorkModelInfoUpdateForm form) {
        if (form == null) {
            return null;
        }
        WorkModelVo workModelVo = new WorkModelVo();
        workModelVo.setId(form.getId());
        workModelVo.setWorkContent(form.getWorkContent());
        workModelVo.setWorkNature(form.getWorkNature());
        workModelVo.setWorkType(form.getWorkType());
        workModelVo.setWorkLevel(form.getWorkLevel());
        workModelVo.setLeaderId(form.getLeaderId());
        workModelVo.setLeaderName(form.getLeaderName());
        workModelVo.setMemberContent(form.getMemberContent());
        workModelVo.setNormIntegral(form.getNormIntegral());
        workModelVo.setPeriod(form.getPeriod());
        workModelVo.setPeriodType(form.getPeriodType());
        workModelVo.setPeriodStatus(form.getPeriodStatus());
        return workModelVo;
    }
    public static WorkModelVo converModelCreate2Entity(WorkModelInfoCreateForm form) {
        if (form == null) {
            return null;
        }
        WorkModelVo workModelVo = new WorkModelVo();
        workModelVo.setWorkContent(form.getWorkContent());
        workModelVo.setWorkNature(form.getWorkNature());
        workModelVo.setWorkType(form.getWorkType());
        workModelVo.setWorkLevel(form.getWorkLevel());
        workModelVo.setLeaderId(form.getLeaderId());
        workModelVo.setLeaderName(form.getLeaderName());
        workModelVo.setMemberContent(form.getMemberContent());
        workModelVo.setNormIntegral(form.getNormIntegral());
        workModelVo.setPeriod(form.getPeriod());
        workModelVo.setPeriodType(form.getPeriodType());
        workModelVo.setPeriodStatus(form.getPeriodStatus());
        return workModelVo;
    }
    private List<WorkModelListPageVo> converModelEntityList2Vo(List<WorkModelVo> records) {
        return records.stream().map(i->converModelEntity2Vo(i)).collect(Collectors.toList());
    }
    public static WorkModelListPageVo converModelEntity2Vo(WorkModelVo i) {
        if (i == null) {
            return null;
        }
        WorkModelListPageVo workModelListPageVo = new WorkModelListPageVo();
        workModelListPageVo.setId(i.getId());
        workModelListPageVo.setModelNo(i.getModelNo());
        workModelListPageVo.setWorkContent(i.getWorkContent());
        workModelListPageVo.setWorkType(i.getWorkType());
        workModelListPageVo.setWorkNature(i.getWorkNature());
        workModelListPageVo.setWorkLevel(i.getWorkLevel());
        workModelListPageVo.setLeaderId(i.getLeaderId());
        workModelListPageVo.setLeaderName(i.getLeaderName());
        workModelListPageVo.setMemberContent(i.getMemberContent());
        workModelListPageVo.setNormIntegral(i.getNormIntegral());
        workModelListPageVo.setPeriod(i.getPeriod());
        workModelListPageVo.setPeriodType(i.getPeriodType());
        workModelListPageVo.setPeriodStatus(i.getPeriodStatus());
        return workModelListPageVo;
    }
}

