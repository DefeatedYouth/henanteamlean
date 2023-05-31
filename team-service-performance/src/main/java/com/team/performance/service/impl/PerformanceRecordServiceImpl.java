package com.team.performance.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.team.common.constants.performance.PerforConstants;
import com.team.performance.dao.PerformancePublishedInfoMapper;
import com.team.performance.dto.publish.QueryClassMemberPerforEvaListDto;
import com.team.performance.entity.PerformancePublishedInfoVo;
import com.team.performance.entity.PerformanceRecordDetailVo;
import com.team.performance.entity.PerformanceRecordVo;
import com.team.performance.dao.PerformanceRecordMapper;
import com.team.performance.entity.TeamMemberInfoVo;
import com.team.performance.service.PerformancePublishedInfoService;
import com.team.performance.service.PerformanceRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 绩效评价记录表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
public class PerformanceRecordServiceImpl extends ServiceImpl<PerformanceRecordMapper, PerformanceRecordVo> implements PerformanceRecordService {

    @Resource
    PerformanceRecordMapper performanceRecordMapper;

    @Autowired
    PerformancePublishedInfoService performancePublishedInfoServiceImpl;

    @Resource
    PerformancePublishedInfoMapper performancePublishedInfoMapper;

    // 查询班员绩效评价
    @Override
    public List<PerformanceRecordVo> queryClassMemberPerforEvaList(QueryClassMemberPerforEvaListDto dto) {
        // todo 根据生产者id查询对应班组编码
        dto.setTeamCode("5");
        List<PerformanceRecordVo> list = Lists.newArrayList();
        // 年份
        String year = dto.getYear();
        // 月份
        String month = dto.getMonth();
        // 季度
        Integer quarter = dto.getQuarter();

        // 如果月份和季度都不存在，则查询最新的一条；否则按照筛选条件查询
        if(StrUtil.isNotEmpty(year))
        {
            // 处理查询时间
            Map<String,Object> map = performancePublishedInfoServiceImpl.getQueryTime(year,month,quarter);

            // 开始时间
            dto.setStartTime(ObjectUtil.isNotEmpty(map.get("startTime")) ? (Date) map.get("startTime") : null);
            // 结束时间
            dto.setEndTime(ObjectUtil.isNotEmpty(map.get("endTime")) ? (Date) map.get("endTime") : null);
            // 周期：1月，2季度
            dto.setAssessmentCycle(ObjectUtil.isNotEmpty(map.get("assessmentCycle")) ? (Integer) map.get("assessmentCycle") : null);

            // 先根据查询条件查询评价记录
            list = performanceRecordMapper.queryClassMemberPerforEvaRecordList(dto);

            // 如果评价记录不存在，表示该时间段的绩效可能还未进行评价，则进行相关处理
            if(CollectionUtil.isEmpty(list))
            {
                list = getMemberPerformanceEvaList(list,dto);
            }
        }else{
            // 查询最近一次的绩效评价
            list = performanceRecordMapper.queryClassMemberPerforEvaList(dto);
        }
        return list;
    }

    // 获取当前查询时间的绩效模板
    private List<PerformanceRecordVo> getMemberPerformanceEvaList(List<PerformanceRecordVo> list,QueryClassMemberPerforEvaListDto dto)
    {
        // 1、根据班组编码查询查询成员列表
//   todo     dto.getTeamCode();
        List<TeamMemberInfoVo> memberList = Lists.newArrayList();
        TeamMemberInfoVo vo = new TeamMemberInfoVo();
        vo.setTeamPeoCode("zm");
        vo.setTeamPeoName("zm");
        memberList.add(vo);

        if(CollectionUtil.isEmpty(memberList)){
            return Lists.newArrayList();
        }

        // 2、根据当前查询月份或季度和班组编码查询对应的绩效模板列表
        List<PerformancePublishedInfoVo> publishList = performancePublishedInfoMapper.queryPublishList(dto);
        if(CollectionUtil.isEmpty(publishList)){
            return Lists.newArrayList();
        }

        // 将两个列表进行数据整合，返回前台
        memberList.stream().forEach(e -> {
            PerformanceRecordVo record = new PerformanceRecordVo();
            record.setTeamCode(dto.getTeamCode());
            record.setTeamPeoCode(e.getTeamPeoCode());
            record.setTeamPeoName(e.getTeamPeoName());
            record.setStatus(PerforConstants.EVA_PUBLISH_STATE.NO_PUBLISH);

            List<PerformanceRecordDetailVo> detailList = Lists.newArrayList();
            publishList.stream().forEach(m -> {
                PerformanceRecordDetailVo detail = new PerformanceRecordDetailVo();
                detail.setQuateName(m.getQuateName());
                detailList.add(detail);

                record.setAssessmentCycle(m.getAssessmentCycle());
                record.setShowTime(m.getShowTime());
            });
            record.setDetailList(detailList);
            list.add(record);
        });

        return list;
    }
}
