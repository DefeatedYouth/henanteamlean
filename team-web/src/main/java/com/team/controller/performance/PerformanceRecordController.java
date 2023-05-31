package com.team.controller.performance;


import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.team.common.constants.result.BaseResult;
import com.team.form.performance.publish.QueryClassMemberPerforEvaListForm;
import com.team.performance.dto.publish.QueryClassMemberPerforEvaListDto;
import com.team.performance.entity.PerformanceRecordDetailVo;
import com.team.performance.entity.PerformanceRecordVo;
import com.team.performance.service.PerformanceRecordService;
import com.team.vo.performance.ClassMemberPerforRecordDetailVo;
import com.team.vo.performance.ClassMemberPerforRecordVo;
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
 * 绩效评价记录表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@RestController
@Api(tags = "班员绩效评价模板管理")
@RequestMapping("/performanceRecordVo")
public class PerformanceRecordController {

    @Autowired
    PerformanceRecordService performanceRecordServiceImpl;

    // 查询班员绩效评价
    @ApiOperation(value = "查询班员绩效评价")
    @RequestMapping(value = "/queryClassMemberPerforEvaList", method = RequestMethod.POST)
    public BaseResult<List<ClassMemberPerforRecordVo>> queryClassMemberPerforEvaList(@RequestBody QueryClassMemberPerforEvaListForm form)
    {
        QueryClassMemberPerforEvaListDto dto = converQueryClassMemListForm2Dto(form);
        List<PerformanceRecordVo> list = performanceRecordServiceImpl.queryClassMemberPerforEvaList(dto);
        // 最终返回列表
        List<ClassMemberPerforRecordVo> recordList = Lists.newArrayList();

        list.stream().forEach(e -> {
            ClassMemberPerforRecordVo record = converQueryList2Vo(e);
            recordList.add(record);
        });
        return success(recordList);
    }

    // 查询班员绩效评价
    public static ClassMemberPerforRecordVo converQueryList2Vo(PerformanceRecordVo e) {
        if (e == null) {
            return null;
        }
        ClassMemberPerforRecordVo classMemberPerforRecordVo = new ClassMemberPerforRecordVo();
        classMemberPerforRecordVo.setId(e.getId());
        classMemberPerforRecordVo.setTeamPeoCode(e.getTeamPeoCode());
        classMemberPerforRecordVo.setTeamPeoName(e.getTeamPeoName());
        classMemberPerforRecordVo.setLastIntegral(e.getLastIntegral());
        classMemberPerforRecordVo.setPerforLevel(e.getPerforLevel());
        classMemberPerforRecordVo.setStatus(e.getStatus());
        // 详细
        List<ClassMemberPerforRecordDetailVo> detailList = Lists.newArrayList();
        if(CollectionUtil.isNotEmpty(e.getDetailList())){
            e.getDetailList().stream().forEach(m -> {
                ClassMemberPerforRecordDetailVo detail = converQueryDetailList2Vo(m);
                detailList.add(detail);
            });
        }
        classMemberPerforRecordVo.setDetailList(detailList);
        return classMemberPerforRecordVo;
    }

    // 查询班员绩效评价
    public static ClassMemberPerforRecordDetailVo converQueryDetailList2Vo(PerformanceRecordDetailVo m) {
        if (m == null) {
            return null;
        }
        ClassMemberPerforRecordDetailVo classMemberPerforRecordDetailVo = new ClassMemberPerforRecordDetailVo();
        classMemberPerforRecordDetailVo.setDetailId(m.getDetailId());
        classMemberPerforRecordDetailVo.setQuateName(m.getQuateName());
        classMemberPerforRecordDetailVo.setRemark(m.getRemark());
        classMemberPerforRecordDetailVo.setIntegral(m.getIntegral());
        return classMemberPerforRecordDetailVo;
    }

    // 查询班员绩效评价
    public static QueryClassMemberPerforEvaListDto converQueryClassMemListForm2Dto(QueryClassMemberPerforEvaListForm form) {
        if (form == null) {
            return null;
        }
        form.verifyParam();
        QueryClassMemberPerforEvaListDto queryClassMemberPerforEvaListDto = new QueryClassMemberPerforEvaListDto();
        queryClassMemberPerforEvaListDto.setProductorId(form.getProductorId());
        queryClassMemberPerforEvaListDto.setYear(form.getYear());
        queryClassMemberPerforEvaListDto.setMonth(form.getMonth());
        queryClassMemberPerforEvaListDto.setQuarter(form.getQuarter());
        return queryClassMemberPerforEvaListDto;
    }
}

