package com.team.work.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.common.constants.WorkConstants;
import com.team.common.exception.CheckException;
import com.team.work.dto.WorkEvaluateUpdateDto;
import com.team.work.dto.WorkMemberChangeUpdateDto;
import com.team.work.dto.WorkMemberListQueryDto;
import com.team.work.entity.WorkInfoVo;
import com.team.work.entity.WorkMemberCheckingVo;
import com.team.work.entity.WorkMemberVo;
import com.team.work.dao.WorkMemberMapper;
import com.team.work.service.WorkInfoService;
import com.team.work.service.WorkMemberCheckingService;
import com.team.work.service.WorkMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 工单人员表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
@Slf4j
public class WorkMemberServiceImpl extends ServiceImpl<WorkMemberMapper, WorkMemberVo> implements WorkMemberService {
    @Autowired
    private WorkInfoService workInfoService;

    @Autowired
    private WorkMemberCheckingService workMemberCheckingService;

    @Autowired
    private Executor taskExecutor;

    @Override
    public List<WorkMemberListQueryDto> queryMemberListByWorkSn(String workNo) {
        HashMap<String, Object> queryMap = MapUtil.newHashMap();
        queryMap.put("WORK_NO",workNo);
        queryMap.put("DELETED",0);
        List<WorkMemberVo> workMemberVos = this.baseMapper.selectByMap(queryMap);
        return converMemberEntityList2Dto(workMemberVos);
    }

    @Override
    public Boolean updateChange(WorkMemberChangeUpdateDto dto) {
        WorkInfoVo workInfoVo = workInfoService.queryWorkByNo(dto.getWorkNo());
        if(ObjectUtil.isNull(workInfoVo)){
            throw new CheckException("查询工单失败:"+dto.getWorkNo());
        }
        if(workInfoVo.getWorkStatus()>= WorkConstants.Status.FINISH.getCode()){
            throw new CheckException("工单状态已改变，无法调整员工:"+dto.getWorkNo());
        }

        QueryWrapper<WorkMemberVo> qw = new QueryWrapper();
        qw.eq("WORK_NO",dto.getWorkNo());
        qw.eq("MEMBER_ID",dto.getMemberId());
        qw.eq("DELETED",0);
        WorkMemberVo workMemberVo = this.baseMapper.selectOne(qw);
        log.info("updateChange查询人员信息{}", JSONObject.toJSONString(workMemberVo));
        WorkMemberVo saveEntity ;
        if(null==workMemberVo){
            if(StrUtil.isEmpty(dto.getWorkDays())){
                throw new CheckException("缺失出勤时间",new Exception("缺失出勤时间"));
            }
            saveEntity = buildMemberEntity(dto);
            this.baseMapper.insert(saveEntity);
            log.info("updateChange新增人员信息{}", JSONObject.toJSONString(saveEntity));
        }else{
            saveEntity = new WorkMemberVo();
            saveEntity.setId(dto.getId());
            if(StrUtil.isEmpty(dto.getWorkDays())){
                if(workMemberVo.getLeaderFlag()==1){
                    throw new CheckException("工单负责人无法删除",new Exception("工单负责人无法删除"));
                }
                saveEntity.setDeleted(1);
                log.info("updateChange删除人员信息{}", JSONObject.toJSONString(saveEntity));
            }else{
                saveEntity.setWorkDays(dto.getWorkDays());
                log.info("updateChange修改人员工作日{}", JSONObject.toJSONString(saveEntity));
            }
            this.baseMapper.updateById(saveEntity);
        }
        //如果是进行中或者完成的订单，调整对应考勤表
        if(workInfoVo.getWorkStatus().equals(WorkConstants.Status.EXECUTE.getCode())||
                workInfoVo.getWorkStatus().equals(WorkConstants.Status.FINISH.getCode())) {
            taskExecutor.execute(()->{
                Boolean b = workMemberCheckingService.changeMemberCheckingByWorkDays(workInfoVo,
                        dto.getMemberId(),dto.getWorkDays(),dto.getMemberName());
                log.info("updateChange修改人员考勤{}", b);
            });
        }
        return true;
    }

    @Override
    public Boolean updateEvaluate(WorkEvaluateUpdateDto dto) {
        WorkInfoVo workInfoVo = workInfoService.queryWorkByNo(dto.getWorkNo());
        if(ObjectUtil.isNull(workInfoVo)){
            throw new CheckException("查询工单失败:"+dto.getWorkNo());
        }
        if(workInfoVo.getWorkStatus().equals(WorkConstants.Status.ARCHIVE.getCode())){
            throw new CheckException("工单状态已改变，无法调整员工:"+dto.getWorkNo());
        }
        WorkMemberVo workMemberVo = this.baseMapper.selectById(dto.getId());
        if(null == workMemberVo){
            throw new CheckException("工单人员信息查询失败",new Exception("工单人员信息查询失败"));
        }
        WorkMemberVo updateMember = new WorkMemberVo();
        updateMember.setId(dto.getId());
        updateMember.setActualStartTime(dto.getActualStartTime());
        updateMember.setActualEndTime(dto.getActualEndTime());
        updateMember.setWorkEvaluate(dto.getWorkEvaluate());
        updateMember.setTotalIntegral(dto.getTotalIntegral());
        updateMember.setAdjustIntegral(dto.getAdjustIntegral());
        updateMember.setFinalIntegral(dto.getFinalIntegral());
        this.baseMapper.updateById(updateMember);
        log.info("updateEvaluate人员评价调整成功{}", JSONObject.toJSONString(updateMember));
        return true;
    }

    @Override
    public Boolean updateMemberInfoByWorkIds(List<WorkMemberCheckingVo> hadWorkVo) {
        String memberId = hadWorkVo.get(0).getMemberId();
        List<String> nos = hadWorkVo.stream().map(i -> i.getWorkNo()).collect(Collectors.toList());
        QueryWrapper<WorkMemberVo> qw = new QueryWrapper();
        qw.eq("DELETED",0);
        qw.eq("MEMBER_ID",memberId);
        qw.in("WORK_NO",nos);
        List<WorkMemberVo> workMemberVos = this.baseMapper.selectList(qw);
        log.info("updateMemberInfoByWorkIds待更新的工单人员表{}",workMemberVos.size());
        if(CollectionUtil.isEmpty(workMemberVos)){
            return true;
        }
        Map<String, WorkMemberVo> workMemberMap = workMemberVos.stream()
                .collect(Collectors.toMap(WorkMemberVo::getWorkNo, Function.identity()));
        for (WorkMemberCheckingVo checkingVo : hadWorkVo) {
            String workNo = checkingVo.getWorkNo();
            WorkMemberVo workMemberVo = workMemberMap.get(workNo);
            if(ObjectUtil.isNull(workMemberVo)){
                log.info("updateMemberInfoByWorkIds工单人员不存在{}",workNo);
                continue;
            }
            //获取工单人员工作日期
            JSONArray workArray = JSONArray.parseArray(workMemberVo.getWorkDays());
            List<String> workDayList = workArray.toJavaList(String.class);
            if(CollectionUtil.contains(workDayList,checkingVo.getWorkDay()+"")){
                workArray.remove(checkingVo.getWorkDay()+"");
                if (workArray.size() == 0) {
                    log.info("updateMemberInfoByWorkIds工单人员考勤为空{}",workMemberVo.getMemberName());
                    if(workMemberVo.getLeaderFlag()==1){
                        throw new CheckException(
                                workMemberVo.getMemberName()+"为工单负责人，出勤天数不能为0");
                    }
                    workMemberVo.setDeleted(1);
                }
                workMemberVo.setWorkDays(workArray.toJSONString());
            }
        }
        List<WorkMemberVo> updateList = workMemberMap.values().stream().map(i -> {
            WorkMemberVo vo = new WorkMemberVo();
            vo.setId(i.getId());
            vo.setWorkDays(i.getWorkDays());
            vo.setDeleted(i.getDeleted());
            return vo;
        }).collect(Collectors.toList());
        log.info("updateMemberInfoByWorkIds需要更新数量{}",updateList.size());
        this.updateBatchById(updateList);

        return true;
    }

    @Override
    public Boolean calculateIntegralByFinish(Integer workId) {
        WorkInfoVo workInfo = workInfoService.getBaseMapper().selectById(workId);
        //查询所有人员
        //查询所有工作人员
        QueryWrapper<WorkMemberVo> qw = new QueryWrapper();
        qw.eq("WORK_NO",workInfo.getWorkNo());
        qw.eq("DELETED",0);
        List<WorkMemberVo> workMemberVos = this.baseMapper.selectList(qw);
        if(CollectionUtil.isEmpty(workMemberVos)){
            return true;
        }
        log.info("calculateIntegralByFinish需要算积分的人员数量{}",workMemberVos.size());
        List<WorkMemberVo> updateList = new ArrayList<>();
        //所有出勤天数
        long allWorkDay = workMemberVos.stream()
                .mapToLong(i -> JSONArray.parseArray(i.getWorkDays()).size()).sum();
        log.info("calculateIntegralByFinish工单{}总出勤时间{}",workInfo.getWorkNo(),allWorkDay);
        //计算人天额定积分
        String normIntegral = workInfo.getNormIntegral();
        String personNorm = NumberUtil.div(normIntegral, allWorkDay + "", 2).toString();
        //人天额定积分*天数*角色系数*任务等级 = 成员积分
        //一般任务1.0，重要任务1.2，紧急任务1.5
        String workCoefficient = getWorkCoefficient(workInfo.getWorkLevel());
        String roleCoefficient = "1.0";

        updateList = workMemberVos.stream().map(i->{
            WorkMemberVo vo = new WorkMemberVo();
            vo.setId(i.getId());
            vo.setActualStartTime(i.getActualStartTime());
            vo.setActualEndTime(i.getActualEndTime());
            calculateActualTime(i,vo);
            vo.setUpdatedBy(workInfo.getUpdatedBy());
            vo.setUpdatedTime(new Date());
            vo.setTotalIntegral(calculateIntegral(i,personNorm,workCoefficient,roleCoefficient));
            vo.setFinalIntegral(vo.getTotalIntegral());
            return vo;
        }).collect(Collectors.toList());
        this.updateBatchById(updateList);
        log.info("calculateIntegralByFinish更新成功{}",updateList.size());
        return true;
    }

    private void calculateActualTime(WorkMemberVo i, WorkMemberVo vo) {
        //计算所有人员实际出勤开始时间，结束时间
        List<Integer> dayList = JSONArray.parseArray(i.getWorkDays())
                .stream()
                .map(a -> Integer.valueOf(String.valueOf(a)))
                .sorted().collect(Collectors.toList());
        vo.setActualStartTime(DateUtil.parse(dayList.get(0)+"",DatePattern.PURE_DATE_PATTERN));
        vo.setActualEndTime(DateUtil.parse(CollectionUtil.getLast(dayList)+"",DatePattern.PURE_DATE_PATTERN));
    }

    private String calculateIntegral(WorkMemberVo member,String personNorm, String workCoefficient, String roleCoefficient) {
        //角色系数，普通1.0，负责人1.5
        if(member.getLeaderFlag()==1){
            roleCoefficient = "1.5";
        }
        int days = JSONArray.parseArray(member.getWorkDays()).size();
        BigDecimal mul = NumberUtil.mul(personNorm,days+"",workCoefficient, roleCoefficient, roleCoefficient);
        String totalIntegral = NumberUtil.round(mul, 2).toString();
        return totalIntegral;
    }

    public String getWorkCoefficient(Integer level){
        String workCoefficient = "1.0";
        if(level.equals(WorkConstants.Level.GENERAL.getCode())){
            workCoefficient = "1.0";
        }else if(level.equals(WorkConstants.Level.URGENCY.getCode())){
            workCoefficient = "1.5";
        }else if(level.equals(WorkConstants.Level.URGENCY.getCode())){
            workCoefficient = "1.2";
        }
        return workCoefficient;
    }
    public static WorkMemberVo buildMemberEntity(WorkMemberChangeUpdateDto dto) {
        if (dto == null) {
            return null;
        }
        WorkMemberVo workMemberVo = new WorkMemberVo();
        workMemberVo.setWorkNo(dto.getWorkNo());
        workMemberVo.setMemberId(dto.getMemberId());
        workMemberVo.setMemberName(dto.getMemberName());
        workMemberVo.setWorkDays(dto.getWorkDays());
        workMemberVo.setCreatedTime(new Date());
        workMemberVo.setCreatedBy("admin");
        return workMemberVo;
    }

    private List<WorkMemberListQueryDto> converMemberEntityList2Dto(List<WorkMemberVo> workMemberVos) {
        List<WorkMemberListQueryDto> dtoList = new ArrayList<>();
        if(CollectionUtil.isEmpty(workMemberVos)){
            return dtoList;
        }
        return workMemberVos.stream().map(i->converMemberEntity2Dto(i)).collect(Collectors.toList());
    }

    public static WorkMemberListQueryDto converMemberEntity2Dto(WorkMemberVo i) {
        if (i == null) {
            return null;
        }
        WorkMemberListQueryDto workMemberListQueryDto = new WorkMemberListQueryDto();
        workMemberListQueryDto.setId(i.getId());
        workMemberListQueryDto.setMemberId(i.getMemberId());
        workMemberListQueryDto.setMemberName(i.getMemberName());
        workMemberListQueryDto.setWorkNo(i.getWorkNo());
        workMemberListQueryDto.setLeaderFlag(i.getLeaderFlag());
        workMemberListQueryDto.setWorkEvaluate(i.getWorkEvaluate());
        workMemberListQueryDto.setTotalIntegral(i.getTotalIntegral());
        workMemberListQueryDto.setAdjustIntegral(i.getAdjustIntegral());
        workMemberListQueryDto.setFinalIntegral(i.getFinalIntegral());
        workMemberListQueryDto.setWorkDays(i.getWorkDays()==null?"":i.getWorkDays());
        workMemberListQueryDto.setActualStartTime(i.getActualStartTime());
        workMemberListQueryDto.setActualEndTime(i.getActualEndTime());
        return workMemberListQueryDto;
    }
}
