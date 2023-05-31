package com.team.work.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.common.constants.WorkConstants;
import com.team.common.exception.CheckException;
import com.team.work.dto.*;
import com.team.work.entity.WorkInfoVo;
import com.team.work.entity.WorkMemberCheckingVo;
import com.team.work.dao.WorkMemberCheckingMapper;
import com.team.work.entity.WorkMemberVo;
import com.team.work.service.WorkInfoService;
import com.team.work.service.WorkMemberCheckingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.work.service.WorkMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 工单员工考勤表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
@Slf4j
public class WorkMemberCheckingServiceImpl extends ServiceImpl<WorkMemberCheckingMapper, WorkMemberCheckingVo> implements WorkMemberCheckingService {
    @Autowired
    private WorkInfoService workInfoService;
    @Autowired
    private WorkMemberService workMemberService;


    @Override
    public WorkMemberCheckingCountDetailDto queryCountDetail(String month, Integer teamId, Integer memberId) {
        WorkMemberCheckingCountDetailDto resultDto = new WorkMemberCheckingCountDetailDto();
        resultDto.setPersonPlans(new ArrayList<>());
        resultDto.setWorkPlans(new ArrayList<>());
        //查询班组总人数
        resultDto.setMemberNum(10);
        log.info("queryCountDetail班组总人数查询成功{}",resultDto.getMemberNum());
        //设置时间区间
        Map queryMap = new HashMap<String,Integer>(4);
        queryMap.put("starTime",Integer.valueOf(month + "00"));
        queryMap.put("endTime",Integer.valueOf(month + "31"));
        queryMap.put("teamId",teamId);
        queryMap.put("memberId",memberId);
        //查询在岗人数
        List<PersonPlanDto> personPlanDtoList = this.baseMapper.queryOnJobCount(queryMap);
        log.info("queryCountDetail在岗人数查询成功{}",personPlanDtoList.size());
        //根据类型分组
        Map<Integer, List<PersonPlanDto>> mapCollect = personPlanDtoList.stream()
                .collect(Collectors.groupingBy(PersonPlanDto::getType));
        for (Integer key : mapCollect.keySet()) {
            List<PersonPlanDto> personPlanDtos = mapCollect.get(key);
            PersonPlanDto dto = new PersonPlanDto();
            dto.setType(key);
            dto.setDayCount(personPlanDtos.size());
            //根据人员分组
            dto.setPersonCount(personPlanDtos.stream()
                    .collect(Collectors.groupingBy(PersonPlanDto::getMemberId)).size());
            resultDto.getPersonPlans().add(dto);
        }

        //查询任务单数量
        List<WorkPlanDto> workPlanDtos = this.baseMapper.queryWorkTypeCount(queryMap);
        resultDto.setWorkPlans(workPlanDtos);
        //总数
        resultDto.setWorkNum(workPlanDtos.stream().collect(Collectors.summingInt(WorkPlanDto::getWorkNum)));
        log.info("queryCountDetail任务单总数查询成功{}",resultDto.getWorkNum());
        return resultDto;
    }

    @Override
    public List<WorkCalendarDto> queryCalendar(String month, Integer teamId) {
        List<WorkCalendarDto> resultList = new ArrayList<>();
        Map queryMap = new HashMap<String,Integer>(2);
        queryMap.put("starTime",Integer.valueOf(month + "00"));
        queryMap.put("endTime",Integer.valueOf(month + "31"));
        queryMap.put("teamId",teamId);
        //查询头部信息
        resultList = buildHeadInfo(resultList,queryMap);
        log.info("queryCalendar查询头部信息成功{}",resultList.size());
        //查询体部信息
        resultList = buildBodyInfo(resultList,queryMap);
        log.info("queryCalendar查询体部信息成功{}",resultList.size());

        return resultList;
    }

    @Override
    public List<WorkLogResultDto> queryWorkLogList(WorkLogQueryDto logQueryForm2Dto) {
        List<WorkLogResultDto> resultList = new ArrayList<>();
        //查询所有记录以天为单位 member_name , day ,content
        List<WorkLogDayListDto> dayResult = this.baseMapper.queryWorkLogContent(logQueryForm2Dto);
        if (CollectionUtil.isEmpty(dayResult)) {
            log.info("queryWorkLogList查询日志信息无日志信息{}", JSONObject.toJSONString(logQueryForm2Dto));
            return resultList;
        }
        log.info("queryWorkLogList查询日志信息{}",dayResult.size());
        Map<Integer, List<WorkLogDayListDto>> memberMap = dayResult.stream()
                .collect(Collectors.groupingBy(WorkLogDayListDto::getMemberId));
        for (Integer memberId : memberMap.keySet()) {
            List<WorkLogDayListDto> logDayList = memberMap.get(memberId);
            WorkLogResultDto dto = new WorkLogResultDto();
            dto.setMemberId(memberId);
            dto.setMemberName(logDayList.get(0).getMemberName());
            dto.setDayInfos(logDayList.stream().map(i -> {
                WorkLogDayInfoDto dayInfo = new WorkLogDayInfoDto();
                dayInfo.setContent(i.getContent());
                dayInfo.setDayAt(String.valueOf(i.getWorkDay()));
                return dayInfo;
            }).collect(Collectors.toList()));
            dto.setRemark(logDayList.stream().distinct()
                    .map(WorkLogDayListDto::getRemark)
                    .collect(Collectors.joining(",")));
            resultList.add(dto);
        }
        log.info("queryWorkLogList查询日志信息循环结束{}",resultList.size());
        return resultList;
    }

    @Override
    @Transactional
    public Boolean createVacate(WorkVacateCreateDto converCreateForm) {
        //组装全部请假数据
        List<WorkMemberCheckingVo> saveList = buildVacateSaveList(converCreateForm);

        //查询所有已存在的假勤
        QueryWrapper<WorkMemberCheckingVo> qw = new QueryWrapper();
        qw.eq("MEMBER_ID",converCreateForm.getMemberId());
        qw.ge("WORK_DAY",Integer.valueOf(converCreateForm.getStartTime()));
        qw.le("WORK_DAY",Integer.valueOf(converCreateForm.getEndTime()));
        qw.eq("DELETED",0);
        List<WorkMemberCheckingVo> workMemberCheckingVos = this.baseMapper.selectList(qw);
        log.info("createVacate待处理旧工单{}",workMemberCheckingVos.size());
        if(CollectionUtil.isNotEmpty(workMemberCheckingVos)){
            //出勤但无工单号：删除
            //已有假勤：删除
            List<WorkMemberCheckingVo> deleteVo = workMemberCheckingVos.stream()
                    .filter(i ->
                    (i.getWorkStatus().equals(WorkConstants.Nature.ATTENDANCE.getCode())
                            && StrUtil.isEmpty(i.getWorkNo())) ||
                    (i.getWorkStatus().equals(WorkConstants.Nature.ABSENCE.getCode())))
                    .collect(Collectors.toList());
            log.info("createVacate出勤但无工单号工单{}",JSONObject.toJSONString(deleteVo));

            //非缺勤且有工单号：查询工单人员表，删除当天的出勤
            List<WorkMemberCheckingVo> hadWorkVo = workMemberCheckingVos.stream()
                    .filter(i -> !i.getWorkStatus().equals(WorkConstants.Nature.ABSENCE.getCode())
                    && StrUtil.isNotEmpty(i.getWorkNo()))
                    .collect(Collectors.toList());
            log.info("createVacate出勤有工单号工单{}",JSONObject.toJSONString(hadWorkVo));
            //判断工单是否已归档，归档不容许调整
            if (CollectionUtil.isNotEmpty(hadWorkVo)) {
                List<String> workNos = workInfoService.checkHadArchive(hadWorkVo.stream()
                        .map(i->i.getWorkNo()).collect(Collectors.toList()));
                if(CollectionUtil.isNotEmpty(workNos)){
                    throw new CheckException("存在已归档工单，无法调整出勤状态");
                }
                //修改对应的工单人员表
                Boolean updateResult = workMemberService.updateMemberInfoByWorkIds(hadWorkVo);
                log.info("createVacate出勤人员更新结果{}",updateResult);
            }
            deleteVo.addAll(hadWorkVo);
            //批量保存
            List<Integer> deleteId = deleteVo.stream()
                    .map(i -> i.getId()).collect(Collectors.toList());
            if(CollectionUtil.isNotEmpty(deleteId)) {
                WorkMemberCheckingVo saveVo = new WorkMemberCheckingVo();
                saveVo.setDeleted(1);
                saveVo.setUpdatedBy("");
                saveVo.setUpdatedTime(new Date());
                qw = new QueryWrapper();
                qw.in("id", deleteId);
                this.baseMapper.update(saveVo, qw);
                log.info("createVacate考勤记录删除成功{}", deleteVo.size());
            }
        }
        this.saveBatch(saveList);
        log.info("createVacate考勤记录新增成功{}",saveList.size());
        return true;
    }

    @Override
    @Transactional
    public Boolean changeMemberCheckingByWorkDays(WorkInfoVo workInfo, String memberId, String workDays, String memberName) {
        String workNo = workInfo.getWorkNo();
        log.info("changeMemberCheckingByWorkDays需要调整的单号{}人员{}",workNo,memberId);
        //数据整理，去除超过今天的，按时间排序
        List<Integer> dayList = JSONArray.parseArray(workDays).stream()
                .filter(i -> DateUtil.compare(DateUtil.parse(String.valueOf(i), DatePattern.PURE_DATE_PATTERN)
                        , new Date()) <= 0)
                .map(i -> Integer.valueOf(String.valueOf(i)))
                .sorted().collect(Collectors.toList());
        log.info("changeMemberCheckingByWorkDays需要调整的天数{}",JSONObject.toJSONString(dayList));
        //查询用户当前工单考勤
        // select from table where member_id = 1 and DELETED = 0 and (work_no = 1 or workday in (1) )
        QueryWrapper<WorkMemberCheckingVo> qw = new QueryWrapper();
        qw.eq("MEMBER_ID",memberId);
        qw.eq("DELETED",0);
        qw.and(wrapper->wrapper.eq("WORK_NO",workNo)
                .or().in("WORK_DAY",dayList));
        List<WorkMemberCheckingVo> checkingVos = this.baseMapper.selectList(qw);

        List<WorkMemberCheckingVo> addList = new ArrayList<>();
        List<Integer> deleteList = new ArrayList<>();
        //已存在的不动，不存在的新增，匹配不上的删除,只有出勤，无工单号的删除
        for (WorkMemberCheckingVo checkingVo : checkingVos) {
            //已存在,工单号相同
            if(dayList.contains(checkingVo.getWorkDay())&&StrUtil.equals(workNo,checkingVo.getWorkNo())){
                dayList.remove(checkingVo.getWorkDay());
                continue;
            }
            //存在出勤，但是无工单号 （默认出勤，或缺勤）
            if(dayList.contains(checkingVo.getWorkDay())&&StrUtil.isEmpty(checkingVo.getWorkNo())){
                deleteList.add(checkingVo.getId());
                continue;
            }
            //不存在 删除
            if(!dayList.contains(checkingVo.getWorkDay())){
                deleteList.add(checkingVo.getId());
                continue;
            }
        }
        addList = dayList.stream().map(i->buildCheckInfo(workInfo,i,memberId,memberName)
        ).collect(Collectors.toList());
        log.info("changeMemberCheckingByWorkDays需要新增的考勤数量{}",addList.size());
        log.info("changeMemberCheckingByWorkDays需要删除的考勤数量{}",deleteList.size());
        if(CollectionUtil.isNotEmpty(deleteList)){
            WorkMemberCheckingVo deleteVo = new WorkMemberCheckingVo();
            deleteVo.setDeleted(1);
            QueryWrapper<WorkMemberCheckingVo> queryWrapper = new QueryWrapper();
            queryWrapper.in("id",deleteList);
            this.baseMapper.update(deleteVo,queryWrapper);
        }
        this.saveBatch(addList);

        log.info("changeMemberCheckingByWorkDays考勤调整成功");
        return true;
    }

    @Override
    public List<WorkMemberCheckingVo> queryAbsenceList(String today) {
        QueryWrapper<WorkMemberCheckingVo> qw = new QueryWrapper();
        qw.eq("DELETE",0);
        qw.eq("WORK_DAY",today);
        qw.eq("WORK_STATUS",WorkConstants.Nature.ABSENCE);
        return this.baseMapper.selectList(qw);
    }

    public static WorkMemberCheckingVo buildCheckInfo(WorkInfoVo workInfo, Integer workDay, String memberId, String memberName) {
        if (workInfo == null) {
            return null;
        }
        WorkMemberCheckingVo workMemberCheckingVo = new WorkMemberCheckingVo();
        workMemberCheckingVo.setId(workInfo.getId());
        workMemberCheckingVo.setWorkNo(workInfo.getWorkNo());
        workMemberCheckingVo.setWorkType(workInfo.getWorkType());
        workMemberCheckingVo.setWorkDay(workDay);
        workMemberCheckingVo.setMemberId(memberId);
        workMemberCheckingVo.setMemberName(memberName);
        workMemberCheckingVo.setStationId(workInfo.getStationId());
        workMemberCheckingVo.setTeamId(workInfo.getTeamId());
        workMemberCheckingVo.setCreatedBy(workInfo.getCreatedBy());
        workMemberCheckingVo.setCreatedTime(workInfo.getCreatedTime());
        workMemberCheckingVo.setDeleted(workInfo.getDeleted());
        return workMemberCheckingVo;
    }
    private List<WorkMemberCheckingVo> buildVacateSaveList(WorkVacateCreateDto converCreateForm) {
        List<WorkMemberCheckingVo> saveList = new ArrayList<>();
        DateTime startTime = DateUtil.parse(converCreateForm.getStartTime(),
                "yyyyMMdd");
        DateTime endTime = DateUtil.parse(converCreateForm.getEndTime(),
                "yyyyMMdd");
        long offsetDayNum = DateUtil.betweenDay(startTime,endTime,true);
        log.info("buildVacateSaveList时间天数{}",offsetDayNum);
        for (int a = 0; a <= offsetDayNum; a++) {
            DateTime workDay = DateUtil.offsetDay(startTime, a);
            WorkMemberCheckingVo saveVo = new WorkMemberCheckingVo();
            saveVo.setMemberId(converCreateForm.getMemberId());
            saveVo.setMemberName(converCreateForm.getMemberName());
            saveVo.setWorkDay(Integer.valueOf(DateUtil.format(workDay,"yyyyMMdd")));
            saveVo.setWorkStatus(WorkConstants.Nature.ABSENCE.getCode());
            saveVo.setLeaveType(converCreateForm.getLeaveType());
            String remarkTime = DateUtil.format(startTime,DatePattern.CHINESE_DATE_PATTERN)+"-"+ DateUtil.format(endTime, DatePattern.CHINESE_DATE_PATTERN);
            saveVo.setRemark(remarkTime+":"+WorkConstants.Leave_Type.getMsgByCode(converCreateForm.getLeaveType()));
            saveVo.setCreatedBy("");
            saveVo.setCreatedTime(new Date());
            saveVo.setTeamId(1);
            saveVo.setStationId(1);
            saveList.add(saveVo);
        }
        log.info("buildVacateSaveList处理条数{}",saveList.size());
        return saveList;
    }
    /**
     * 查询体信息
     * @param resultList
     * @param queryMap
     */
    private List<WorkCalendarDto> buildBodyInfo(List<WorkCalendarDto> resultList, Map queryMap) {
        List<WorkCalendarPlanDto> bodyDtos = new ArrayList<>();
        if(ObjectUtil.isNull(queryMap.get("teamId"))){
            bodyDtos = this.baseMapper.queryCalendarBodyInfo(queryMap);
        }else{
            bodyDtos = this.baseMapper.queryCalendarBodyInfoByTeamId(queryMap);
        }
        Map<Integer, List<WorkCalendarPlanDto>> bodyMap = bodyDtos.stream()
                .collect(Collectors.groupingBy(WorkCalendarPlanDto::getWorkDay));
        Map<Integer, WorkCalendarDto> resultListMap = resultList.stream()
                .collect(Collectors.toMap(WorkCalendarDto::getDayAt, Function.identity()));
        for (Integer key : bodyMap.keySet()) {
            List<WorkCalendarPlanDto> workCalendarPlanDtos = bodyMap.get(key);
            WorkCalendarDto workCalendarDto = resultListMap.get(key);
            if(null!=workCalendarDto){
                workCalendarDto.setWorkPlans(workCalendarPlanDtos);
            }
        }
        resultList = resultListMap.values().stream().collect(Collectors.toList());
        return resultList;
    }
    /**
     * 查询头部信息
     * @param resultList
     * @param queryMap
     */
    private List<WorkCalendarDto> buildHeadInfo(List<WorkCalendarDto> resultList, Map queryMap) {
        List<CalendarHeadInfoDto> headDtos = this.baseMapper.queryCalendarHeadInfo(queryMap);
        //组装头部信息
        Map<Integer, List<CalendarHeadInfoDto>> headMap = headDtos.stream()
                .collect(Collectors.groupingBy(CalendarHeadInfoDto::getWorkDay));
        for (Integer key : headMap.keySet()) {
            WorkCalendarDto dto = new WorkCalendarDto();
            dto.setDayAt(key);
            List<CalendarHeadInfoDto> headInfoDtos = headMap.get(key);
            headInfoDtos.forEach(i->{
                if(i.getWorkStatus()==0){
                    dto.setOnWorkCount(i.getCount());
                }else if(i.getWorkStatus()>=0&&i.getWorkStatus()<4){
                    dto.setOnTripCount(i.getCount());
                }else{
                    dto.setOnLeaveCount(i.getCount());
                }
            });
            resultList.add(dto);
        }
        return resultList;
    }
}
