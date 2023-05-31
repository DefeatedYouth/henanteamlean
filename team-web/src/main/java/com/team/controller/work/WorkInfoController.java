package com.team.controller.work;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.common.constants.WorkConstants;
import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.form.work.*;
import com.team.vo.work.WorkHomePageQueryListVo;
import com.team.vo.work.WorkListExportVo;
import com.team.vo.work.WorkListPageMemberVo;
import com.team.vo.work.WorkListPageVo;
import com.team.work.dto.*;
import com.team.work.entity.WorkInfoVo;
import com.team.work.service.WorkInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 工单表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@RestController
@RequestMapping("/workInfo")
@Slf4j
@Api(value = "/workInfo", tags = "工单模块")
public class WorkInfoController extends BaseController {
    @Autowired
    private WorkInfoService workInfoService;


    /**
     * 查询列表
     */
    @ApiOperation(value = "工单分页列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public BaseResult<List<WorkListPageVo>> list(@RequestBody WorkListPageForm form) {
        WorkListPageDto dto = converListForm2Dto(form);
        Page<WorkListPageResultDto> resultDtoList = workInfoService.queryWorkList(dto);
        List<WorkListPageVo> resultList = converDto2Vo(resultDtoList.getRecords());
        BaseResult<List<WorkListPageVo>> success = success(resultList);
        success.setPages(resultDtoList.getTotal());
        return success;
    }

    /**
     * 主页列表
     */
    @ApiOperation(value = "工单主页列表")
    @RequestMapping(value = "/homeList", method = RequestMethod.POST)
    public BaseResult<List<WorkHomePageQueryListVo>> homeList(@RequestBody WorkHomePageQueryForm form) {
        WorkHomePageDto dto = converHomeListForm2Dto(form);
        Page<WorkInfoVo> resultDtoList = workInfoService.queryHomeList(dto);
        BaseResult<List<WorkHomePageQueryListVo>> success = success(converHomeList2Vo(resultDtoList.getRecords()));
        success.setPages(resultDtoList.getTotal());
        return success;
    }
    /**
     * 进度控制
     */
    @ApiOperation(value = "工单主页控制")
    @RequestMapping(value = "/homeUpdate", method = RequestMethod.POST)
    public BaseResult<Boolean> homeUpdate(@RequestBody WorkHomePageUpdateForm form) {
        WorkHomeUpdateDto dto = converHomeUpdateForm2Dto(form);
        Boolean result = workInfoService.homeUpdate(dto);
        return success(result);
    }

    /**
     * 新增工单
     */
    @ApiOperation(value = "工单新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult<String> add(@RequestBody WorkInfoCreateForm form) {
        WorkInfoCreateDto dto = converAddForm2Dto(form);
        dto.setWorkSource(WorkConstants.Source.HAND_WORK.getCode());
        String workNo = workInfoService.createWork(dto);
        return success(workNo);
    }

    /**
     * 删除工单
     */
    @ApiOperation(value = "工单删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult<Boolean> delete(@RequestBody WorkInfoDeleteForm form) {
        Boolean deleteResult = workInfoService.deleteWork(form.getId());
        return success(deleteResult);
    }

    /**
     * 取消工单
     */
    @ApiOperation(value = "工单取消")
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public BaseResult<Boolean> cancel(@RequestBody WorkInfoCancelForm form) {
        Boolean cancelResult = workInfoService.cancelWork(form.getId());
        return success(cancelResult);
    }

    /**
     * 发布工单
     */
    @ApiOperation(value = "工单发布")
    @RequestMapping(value = "/push", method = RequestMethod.POST)
    public BaseResult<Boolean> push(@RequestBody WorkInfoPushForm form) {
        Boolean pushResult = workInfoService.pushWork(form.getId());
        return success(pushResult);
    }

    /**
     * 归档工单
     */
    @ApiOperation(value = "工单归档")
    @RequestMapping(value = "/archive", method = RequestMethod.POST)
    public BaseResult<Boolean> archive(@RequestBody WorkInfoPushForm form) {
        Boolean archiveResult = workInfoService.archiveWork(form.getId());
        return success(archiveResult);
    }

    /**
     * 修改工单
     */
    @ApiOperation(value = "工单修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult<Boolean> update(@RequestBody WorkInfoUpdateForm form) {
        WorkUpdateDto dto = converUpdateForm2Dto(form);
        Boolean archiveResult = workInfoService.updateWork(dto);
        return success(archiveResult);
    }

    /**
     * 导出列表
     */
    @ApiOperation(value = "工单导出")
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(HttpServletResponse response,@RequestBody WorkListPageForm form) {
        form.setPageNum(1);
        form.setPageSize(3000);
        WorkListPageDto dto = converListForm2Dto(form);
        Page<WorkListPageResultDto> resultDtoList = workInfoService.queryWorkList(dto);
        List<WorkListExportVo> resultList = converExportDto2Vo(resultDtoList.getRecords());

        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.addHeaderAlias("no","序号");
        writer.addHeaderAlias("workContent","工作内容");
        writer.addHeaderAlias("workType","任务类型");
        writer.addHeaderAlias("workNature","工作性质");
        writer.addHeaderAlias("planStartTime","计划开始时间");
        writer.addHeaderAlias("planEndTime","计划结束时间");
        writer.addHeaderAlias("leaderName","工作负责人");
        writer.addHeaderAlias("memberNames","工作成员");
        writer.addHeaderAlias("workLevel","任务等级");
        writer.addHeaderAlias("normIntegral","工单定额积分");
        writer.addHeaderAlias("actualStartTime","实际开始时间");
        writer.addHeaderAlias("actualEndTime","实际结束时间");
        writer.addHeaderAlias("workStatus","任务单状态");
        writer.addHeaderAlias("workEvaluate","工作评价");
        writer.addHeaderAlias("memberTotalIntegral","系统计算积分");
        writer.addHeaderAlias("memberFinalIntegral","最终积分");
        writer.addHeaderAlias("remark","备注");

        //默认配置
        writer.write(resultList,true);
        //设置content—type
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset:utf-8");
        //设置标题
        String fileName= null;
        try {
            fileName = URLEncoder.encode("工作任务单","UTF-8");
            //Content-disposition是MIME协议的扩展，MIME协议指示MIME用户代理如何显示附加的文件。
            response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
            ServletOutputStream outputStream= response.getOutputStream();
            //将Writer刷新到OutPut
            writer.flush(outputStream,true);
            outputStream.close();
            writer.close();
        } catch (Exception e) {
            log.error("export导出表单异常"+e);
            e.printStackTrace();
        }
    }
    private List<WorkHomePageQueryListVo> converHomeList2Vo(List<WorkInfoVo> records) {
        List<WorkHomePageQueryListVo> resultList = new ArrayList<>();
        if(CollectionUtil.isEmpty(records)){
            return resultList;
        }
        return records.stream().map(i->converHomeEntity2Vo(i))
                .collect(Collectors.toList());
    }
    public static WorkHomePageQueryListVo converHomeEntity2Vo(WorkInfoVo i) {
        if (i == null) {
            return null;
        }
        WorkHomePageQueryListVo workHomePageQueryListVo = new WorkHomePageQueryListVo();
        workHomePageQueryListVo.setId(i.getId());
        workHomePageQueryListVo.setWorkContent(i.getWorkContent());
        workHomePageQueryListVo.setWorkStatus(i.getWorkStatus());
        workHomePageQueryListVo.setPlanStartTime(i.getPlanStartTime());
        workHomePageQueryListVo.setPlanEndTime(i.getPlanEndTime());
        workHomePageQueryListVo.setActualStartTime(i.getActualStartTime());
        workHomePageQueryListVo.setActualEndTime(i.getActualEndTime());
        return workHomePageQueryListVo;
    }
    public static WorkHomePageDto converHomeListForm2Dto(WorkHomePageQueryForm form) {
        if (form == null) {
            return null;
        }
        WorkHomePageDto workHomePageDto = new WorkHomePageDto();
        workHomePageDto.setQueryType(form.getQueryType());
        workHomePageDto.setPageSize(form.getPageSize());
        workHomePageDto.setPageNum(form.getPageNum());
        return workHomePageDto;
    }
    private List<WorkListExportVo> converExportDto2Vo(List<WorkListPageResultDto> records) {
        List<WorkListExportVo> resultList = new ArrayList<>();
        if(CollectionUtil.isEmpty(records)){
            return resultList;
        }
        int index = 1;
        for (WorkListPageResultDto record : records) {
            WorkListExportVo workListExportVo = converExportDto(record);
            workListExportVo.setNo(index);
            index++;
            resultList.add(workListExportVo);
        }
        return resultList;
    }
    public static WorkListExportVo converExportDto(WorkListPageResultDto i) {
        if (i == null) {
            return null;
        }
        WorkListExportVo workListExportVo = new WorkListExportVo();
        workListExportVo.setWorkNo(i.getWorkNo());
        workListExportVo.setWorkContent(i.getWorkContent());
        workListExportVo.setWorkType(WorkConstants.Type.getMsgByCode(i.getWorkType()));
        workListExportVo.setWorkNature(WorkConstants.Nature.getMsgByCode(i.getWorkNature()));
        workListExportVo.setPlanStartTime(DateUtil.format(i.getPlanStartTime(), DatePattern.NORM_DATE_PATTERN));
        workListExportVo.setPlanEndTime(DateUtil.format(i.getPlanEndTime(), DatePattern.NORM_DATE_PATTERN));
        workListExportVo.setLeaderId(i.getLeaderId());
        workListExportVo.setLeaderName(i.getLeaderName());
        workListExportVo.setWorkLevel(WorkConstants.Level.getMsgByCode(i.getWorkLevel()));
        workListExportVo.setWorkStatus(WorkConstants.Status.getMsgByCode(i.getWorkStatus()));
        workListExportVo.setWorkEvaluate(WorkConstants.Evaluate.getMsgByCode(i.getWorkEvaluate()));
        workListExportVo.setActualStartTime(DateUtil.format(i.getActualStartTime(), DatePattern.NORM_DATE_PATTERN));
        workListExportVo.setActualEndTime(DateUtil.format(i.getActualEndTime(), DatePattern.NORM_DATE_PATTERN));
        workListExportVo.setRemark(i.getRemark());
        workListExportVo.setNormIntegral(i.getNormIntegral());
        if(CollectionUtil.isNotEmpty(i.getMemberVoList())){
            String memberNames = i.getMemberVoList().stream()
                    .map(WorkListPageMemberDto::getMemberName)
                    .collect(Collectors.joining(","));
            workListExportVo.setMemberNames(memberNames);
            String memberTotal = i.getMemberVoList().stream()
                    .map(m-> StrUtil.builder(m.getMemberName(),":",m.getTotalIntegral()))
                    .collect(Collectors.joining(","));
            workListExportVo.setMemberTotalIntegral(memberTotal);
            String memberFinal = i.getMemberVoList().stream()
                    .map(m-> StrUtil.builder(m.getMemberName(),":",m.getFinalIntegral()))
                    .collect(Collectors.joining(","));
            workListExportVo.setMemberFinalIntegral(memberFinal);
        }
        return workListExportVo;
    }
    public static WorkUpdateDto converUpdateForm2Dto(WorkInfoUpdateForm form) {
        if (form == null) {
            return null;
        }
        WorkUpdateDto workUpdateDto = new WorkUpdateDto();
        workUpdateDto.setId(form.getId());
        workUpdateDto.setWorkContent(form.getWorkContent());
        workUpdateDto.setWorkNature(form.getWorkNature());
        workUpdateDto.setWorkType(form.getWorkType());
        workUpdateDto.setWorkLevel(form.getWorkLevel());
        workUpdateDto.setLeaderId(form.getLeaderId());
        workUpdateDto.setLeaderName(form.getLeaderName());
        workUpdateDto.setPlanStartTime(form.getPlanStartTime());
        workUpdateDto.setPlanEndTime(form.getPlanEndTime());
        workUpdateDto.setActualStartTime(form.getActualStartTime());
        workUpdateDto.setActualEndTime(form.getActualEndTime());
        workUpdateDto.setNormIntegral(form.getNormIntegral());
        workUpdateDto.setWorkEvaluate(form.getWorkEvaluate());
        if(CollectionUtil.isNotEmpty(form.getMemberList())){
            workUpdateDto.setMemberList(form.getMemberList()
                    .stream()
                    .map(i->converMemberForm2Dto(i))
                    .collect(Collectors.toList()));
        }

        return workUpdateDto;
    }
    public static WorkInfoCreateDto converAddForm2Dto(WorkInfoCreateForm form) {
        if (form == null) {
            return null;
        }
        WorkInfoCreateDto workInfoCreateDto = new WorkInfoCreateDto();
        workInfoCreateDto.setWorkContent(form.getWorkContent());
        workInfoCreateDto.setWorkNature(form.getWorkNature());
        workInfoCreateDto.setWorkType(form.getWorkType());
        workInfoCreateDto.setWorkLevel(form.getWorkLevel());
        workInfoCreateDto.setLeaderId(form.getLeaderId());
        workInfoCreateDto.setLeaderName(form.getLeaderName());
        workInfoCreateDto.setPlanStartTime(form.getPlanStartTime());
        workInfoCreateDto.setPlanEndTime(form.getPlanEndTime());
        workInfoCreateDto.setNormIntegral(form.getNormIntegral());
        if(CollectionUtil.isNotEmpty(form.getMemberList())) {
            workInfoCreateDto.setMemberList(form.getMemberList()
                    .stream()
                    .map(i->converMemberForm2Dto(i))
                    .collect(Collectors.toList()));
        }
        return workInfoCreateDto;
    }
    public static WorkListPageMemberDto converMemberForm2Dto(WorkListPageMemberForm i) {
        if (i == null) {
            return null;
        }
        WorkListPageMemberDto workListPageMemberDto = new WorkListPageMemberDto();
        workListPageMemberDto.setMemberId(i.getMemberId());
        workListPageMemberDto.setMemberName(i.getMemberName());
        return workListPageMemberDto;
    }
    private static List<WorkListPageVo> converDto2Vo(List<WorkListPageResultDto> resultDtoList) {
        List<WorkListPageVo> listPageVos = resultDtoList
                .stream()
                .map(i->converWorkDto2Vo(i))
                .collect(Collectors.toList());
        return listPageVos;
    }
    public static WorkListPageVo converWorkDto2Vo(WorkListPageResultDto workListPageResultDto) {
        if (workListPageResultDto == null) {
            return null;
        }
        WorkListPageVo workListPageVo = new WorkListPageVo();
        workListPageVo.setId(workListPageResultDto.getId());
        workListPageVo.setWorkNo(workListPageResultDto.getWorkNo());
        workListPageVo.setWorkContent(workListPageResultDto.getWorkContent());
        workListPageVo.setWorkType(workListPageResultDto.getWorkType());
        workListPageVo.setWorkNature(workListPageResultDto.getWorkNature());
        workListPageVo.setPlanStartTime(workListPageResultDto.getPlanStartTime());
        workListPageVo.setPlanEndTime(workListPageResultDto.getPlanEndTime());
        workListPageVo.setLeaderId(workListPageResultDto.getLeaderId());
        workListPageVo.setLeaderName(workListPageResultDto.getLeaderName());
        if(CollectionUtil.isNotEmpty(workListPageResultDto.getMemberVoList())){
            workListPageVo.setMemberVoList(workListPageResultDto.getMemberVoList()
                    .stream()
                    .map(i->converMemberDto2Vo(i))
                    .collect(Collectors.toList()));
        }
        workListPageVo.setWorkLevel(workListPageResultDto.getWorkLevel());
        workListPageVo.setWorkStatus(workListPageResultDto.getWorkStatus());
        workListPageVo.setWorkEvaluate(workListPageResultDto.getWorkEvaluate());
        workListPageVo.setActualStartTime(workListPageResultDto.getActualStartTime());
        workListPageVo.setActualEndTime(workListPageResultDto.getActualEndTime());
        workListPageVo.setRemark(workListPageResultDto.getRemark());
        workListPageVo.setNormIntegral(workListPageResultDto.getNormIntegral());
        return workListPageVo;
    }
    public static WorkListPageMemberVo converMemberDto2Vo(WorkListPageMemberDto i) {
        if (i == null) {
            return null;
        }
        WorkListPageMemberVo workListPageMemberVo = new WorkListPageMemberVo();
        workListPageMemberVo.setMemberId(i.getMemberId());
        workListPageMemberVo.setMemberName(i.getMemberName());
        workListPageMemberVo.setTotalIntegral(i.getTotalIntegral());
        workListPageMemberVo.setFinalIntegral(i.getFinalIntegral());
        return workListPageMemberVo;
    }
    public static WorkListPageDto converListForm2Dto(WorkListPageForm form) {
        if (form == null) {
            return null;
        }
        WorkListPageDto workListPageDto = new WorkListPageDto();
        workListPageDto.setPageSize(form.getPageSize());
        workListPageDto.setPageNum(form.getPageNum());
        workListPageDto.setWorkNature(form.getWorkNature());
        workListPageDto.setWorkType(form.getWorkType());
        workListPageDto.setWorkLevel(form.getWorkLevel());
        workListPageDto.setWorkStatus(form.getWorkStatus());
        workListPageDto.setWorkEvaluate(form.getWorkEvaluate());
        workListPageDto.setWorkContent(form.getWorkContent());
        workListPageDto.setPlanStartTime(form.getPlanStartTime());
        workListPageDto.setPlanEndTime(form.getPlanEndTime());
        workListPageDto.setLeaderName(form.getLeaderName());
        return workListPageDto;
    }
    public static WorkHomeUpdateDto converHomeUpdateForm2Dto(WorkHomePageUpdateForm form) {
        if (form == null) {
            return null;
        }
        WorkHomeUpdateDto workHomeUpdateDto = new WorkHomeUpdateDto();
        workHomeUpdateDto.setId(form.getId());
        workHomeUpdateDto.setWorkStatus(form.getWorkStatus());
        workHomeUpdateDto.setWorkEvaluate(form.getWorkEvaluate());
        workHomeUpdateDto.setActualStartTime(form.getActualStartTime());
        workHomeUpdateDto.setActualEndTime(form.getActualEndTime());
        return workHomeUpdateDto;
    }

}

