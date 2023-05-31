package com.team.work.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.common.constants.WorkConstants;
import com.team.common.exception.CheckException;
import com.team.work.dao.WorkMemberMapper;
import com.team.work.dto.*;
import com.team.work.entity.WorkInfoVo;
import com.team.work.dao.WorkInfoMapper;
import com.team.work.entity.WorkMemberVo;
import com.team.work.entity.WorkModelVo;
import com.team.work.entity.WorkRecordVo;
import com.team.work.service.WorkInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.work.service.WorkMemberCheckingService;
import com.team.work.service.WorkMemberService;
import com.team.work.service.WorkRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * <p>
 * 工单表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
@Slf4j
public class WorkInfoServiceImpl extends ServiceImpl<WorkInfoMapper, WorkInfoVo> implements WorkInfoService {
    @Autowired
    private WorkInfoMapper workInfoMapper;

    @Autowired
    private WorkMemberMapper workMemberMapper;

    @Autowired
    private WorkRecordService workRecordService;

    @Autowired
    private Executor taskExecutor;

    @Autowired
    private WorkMemberService workMemberService;

    @Autowired
    private WorkMemberCheckingService workMemberCheckingService;

    @Override
    public Page<WorkListPageResultDto> queryWorkList(WorkListPageDto dto) {
        Page<WorkListPageResultDto> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        Page<WorkListPageResultDto> pageResult = this.baseMapper.queryWorkList(page, dto);
        return pageResult;
    }

    @Override
    @Transactional
    public String createWork(WorkInfoCreateDto dto) {
        //工单整体赋值
        WorkInfoVo workInfo = buildWorkEntity(dto);
        workInfo.setWorkStatus(WorkConstants.Status.NEW.getCode());
        //fixme 待处理
        workInfo.setCreatedBy("admin");
        workInfo.setCreatedTime(new Date());
        baseMapper.insert(workInfo);
        log.info("保存工单成功{}",workInfo.getWorkNo());
        //人员表初始化,负责人数据加入
        List<WorkMemberVo> workMembers = buildWorkMember(dto,workInfo);
        workMemberService.saveBatch(workMembers);
        log.info("保存工单人员成功{}",workMembers.size());
        //工单历史记录表
        workRecordService.saveRecord(workInfo,WorkConstants.Record_Type.NEW,"");

        return workInfo.getWorkNo();
    }

    @Override
    public Boolean deleteWork(Integer id) {
        WorkInfoVo workInfoVo = this.baseMapper.selectById(id);
        if(null==workInfoVo){
            throw new CheckException("查询工单失败:"+id);
        }
        if(0!=workInfoVo.getDeleted()){
            throw new CheckException("工单已删除:"+workInfoVo.getWorkNo());
        }
        if(workInfoVo.getWorkStatus()>WorkConstants.Status.WAIT.getCode()){
            throw new CheckException("工单已执行，无法删除:"+workInfoVo.getWorkNo());
        }
        WorkInfoVo saveEntity = new WorkInfoVo();
        saveEntity.setId(workInfoVo.getId());
        saveEntity.setWorkNo(workInfoVo.getWorkNo());
        saveEntity.setDeleted(WorkConstants.Delete.DELETE);
        this.baseMapper.updateById(saveEntity);
        //工单历史记录表
        workRecordService.saveRecord(saveEntity,WorkConstants.Record_Type.DELETE,"");

        return true;
    }

    @Override
    public Boolean cancelWork(Integer id) {
        WorkInfoVo workInfoVo = this.baseMapper.selectById(id);
        if(null==workInfoVo){
            throw new CheckException("查询工单失败:"+id);
        }
        if(0!=workInfoVo.getDeleted()){
            throw new CheckException("工单已删除:"+workInfoVo.getWorkNo());
        }
        if(workInfoVo.getWorkStatus()>WorkConstants.Status.WAIT.getCode()){
            throw new CheckException("工单已执行，无法取消:"+workInfoVo.getWorkNo());
        }

        WorkInfoVo saveEntity = new WorkInfoVo();
        saveEntity.setId(workInfoVo.getId());
        saveEntity.setWorkNo(workInfoVo.getWorkNo());
        saveEntity.setWorkStatus(WorkConstants.Status.CANCEL.getCode());
        this.baseMapper.updateById(saveEntity);
        //工单历史记录表
        workRecordService.saveRecord(saveEntity,WorkConstants.Record_Type.CANCEL,"");

        return true;
    }

    @Override
    public Boolean pushWork(Integer id) {
        WorkInfoVo workInfoVo = this.baseMapper.selectById(id);
        if(null==workInfoVo){
            throw new CheckException("查询工单失败:"+id);
        }
        if(0!=workInfoVo.getDeleted()){
            throw new CheckException("工单已删除:"+workInfoVo.getWorkNo());
        }
        if(!workInfoVo.getWorkStatus().equals(WorkConstants.Status.NEW.getCode())){
            throw new CheckException("工单状态已改变，无法推送:"+workInfoVo.getWorkNo());
        }
        checkWorkInfoEmpty(workInfoVo);

        WorkInfoVo saveEntity = new WorkInfoVo();
        saveEntity.setId(workInfoVo.getId());
        saveEntity.setWorkNo(workInfoVo.getWorkNo());
        saveEntity.setWorkStatus(WorkConstants.Status.WAIT.getCode());
        this.baseMapper.updateById(saveEntity);

        //工单历史记录表
        workRecordService.saveRecord(saveEntity,WorkConstants.Record_Type.PUSH,
                JSONObject.toJSONString(workInfoVo));

        return true;
    }

    @Override
    public Boolean archiveWork(Integer id) {
        WorkInfoVo workInfoVo = this.baseMapper.selectById(id);
        if(null==workInfoVo){
            throw new CheckException("查询工单失败:"+id);
        }
        if(0!=workInfoVo.getDeleted()){
            throw new CheckException("工单已删除:"+workInfoVo.getWorkNo());
        }
        if(!workInfoVo.getWorkStatus().equals(WorkConstants.Status.FINISH.getCode())){
            throw new CheckException("工单状态已改变，无法归档:"+workInfoVo.getWorkNo());
        }
        checkWorkArchiveInfoEmpty(workInfoVo);

        WorkInfoVo saveEntity = new WorkInfoVo();
        saveEntity.setId(workInfoVo.getId());
        saveEntity.setWorkNo(workInfoVo.getWorkNo());
        saveEntity.setWorkStatus(WorkConstants.Status.ARCHIVE.getCode());
        this.baseMapper.updateById(saveEntity);

        //工单历史记录表
        workRecordService.saveRecord(saveEntity,WorkConstants.Record_Type.ARCHIVE,
                JSONObject.toJSONString(workInfoVo));
        return true;
    }

    @Override
    public Boolean updateWork(WorkUpdateDto dto) {
        WorkInfoVo workInfoVo = this.baseMapper.selectById(dto.getId());
        if(null==workInfoVo){
            throw new CheckException("查询工单失败:"+dto.getId());
        }
        if(0!=workInfoVo.getDeleted()){
            throw new CheckException("工单已删除:"+workInfoVo.getWorkNo());
        }
        //判断工单状态
        if(workInfoVo.getWorkStatus()>WorkConstants.Status.FINISH.getCode()){
            throw new CheckException("工单状态已改变，无法修改:"+workInfoVo.getWorkNo());
        }

        WorkInfoVo updateWork;
        if (workInfoVo.getWorkStatus().equals(WorkConstants.Status.NEW.getCode())||
                workInfoVo.getWorkStatus().equals(WorkConstants.Status.CANCEL.getCode())) {
            log.info("updateWork工单创建状态数据调整{}",JSONObject.toJSONString(dto));
            updateWork = updateWorkByCreate(workInfoVo,dto);
        } else {
            log.info("updateWork工单运行状态数据调整{}",JSONObject.toJSONString(dto));
            updateWork = updateWorkByWait(workInfoVo,dto);
        }

        //有开工时间,工单旧状态待执行   开工状态
        Boolean executeFlage = false;
        Boolean finishFlage = false;
        if(ObjectUtil.isNotNull(dto.getActualStartTime())
                &&workInfoVo.getWorkStatus().equals(WorkConstants.Status.WAIT.getCode())){
            updateWork.setWorkStatus(WorkConstants.Status.EXECUTE.getCode());
            updateWork.setActualStartTime(dto.getActualStartTime());
            executeFlage = true;
        //有完工时间，工单旧状态执行中     完工状态
        }else if(ObjectUtil.isNotNull(dto.getActualEndTime())
                &&workInfoVo.getWorkStatus().equals(WorkConstants.Status.EXECUTE.getCode())){
            updateWork.setWorkStatus(WorkConstants.Status.FINISH.getCode());
            updateWork.setActualEndTime(dto.getActualEndTime());
            finishFlage = true;
        }else if(workInfoVo.getWorkStatus().equals(WorkConstants.Status.FINISH.getCode())){
            updateWork.setActualStartTime(dto.getActualStartTime());
            updateWork.setActualEndTime(dto.getActualEndTime());
        }
        updateWork.setWorkNo(workInfoVo.getWorkNo());
        this.baseMapper.updateById(updateWork);
        //工单历史记录表
        workRecordService.saveRecord(updateWork,WorkConstants.Record_Type.UPDATE,
                JSONObject.toJSONString(dto));

        //计算考勤 计算工单分数
        if(executeFlage){
            taskExecutor.execute(()->{
                changeMemberCheckingByExecute(workInfoVo);
            });
        } else if(finishFlage){
            taskExecutor.execute(()->{
                workMemberService.calculateIntegralByFinish(updateWork.getId());
            });
        }
        return true;
    }

    @Override
    public List<String> checkHadArchive(List<String> collect) {
        QueryWrapper<WorkInfoVo> qw = new QueryWrapper();
        qw.in("WORK_NO",collect);
        qw.eq("DELETED",0);
        qw.eq("WORK_STATUS",4);
        List<WorkInfoVo> workInfoVos = this.baseMapper.selectList(qw);
        if(CollectionUtil.isEmpty(workInfoVos)){
            return new ArrayList<>();
        }else {
            return workInfoVos.stream().map(i->i.getWorkNo()).collect(Collectors.toList());
        }
    }

    @Override
    public Page<WorkInfoVo> queryHomeList(WorkHomePageDto dto) {
        Page<WorkInfoVo> producePage = new Page<>(dto.getPageNum(),dto.getPageSize());
        Page<WorkInfoVo> page = this.baseMapper.queryHomeList(producePage,dto);
        return page;
    }

    @Override
    public Boolean homeUpdate(WorkHomeUpdateDto dto) {
        log.info("homeUpdate首页更新订单{}",JSONObject.toJSONString(dto));
        //查询出工单
        WorkInfoVo workInfoVo = this.baseMapper.selectById(dto.getId());
        if(null==workInfoVo){
            throw new CheckException("查询工单失败:"+dto.getId());
        }
        if(0!=workInfoVo.getDeleted()){
            throw new CheckException("工单已删除:"+workInfoVo.getWorkNo());
        }
        //判断工单状态
        checkWorkStatus(dto,workInfoVo);
        WorkInfoVo saveVo = new WorkInfoVo();
        saveVo.setId(dto.getId());
        saveVo.setWorkNo(workInfoVo.getWorkNo());
        saveVo.setWorkStatus(dto.getWorkStatus());
        saveVo.setWorkEvaluate(dto.getWorkEvaluate());
        saveVo.setActualStartTime(dto.getActualStartTime());
        saveVo.setActualEndTime(dto.getActualEndTime());
        log.info("homeUpdate数据校验完成{}",JSONObject.toJSONString(saveVo));
        this.baseMapper.updateById(saveVo);

        //工单历史记录表
        workRecordService.saveRecord(saveVo,WorkConstants.Record_Type.UPDATE,
                JSONObject.toJSONString(dto));

        //完工工单计算积分
        //开工计算考勤 计算工单分数
        Boolean executeFlage = saveVo.getWorkStatus().equals(WorkConstants.Status.EXECUTE.getCode());
        Boolean finishFlage = saveVo.getWorkStatus().equals(WorkConstants.Status.FINISH.getCode());
        if(executeFlage){
            taskExecutor.execute(()->{
                changeMemberCheckingByExecute(workInfoVo);
            });
        } else if(finishFlage){
            taskExecutor.execute(()->{
                saveVo.setNormIntegral(workInfoVo.getNormIntegral());
                saveVo.setWorkLevel(workInfoVo.getWorkLevel());
                workMemberService.calculateIntegralByFinish(saveVo.getId());
            });
        }
        return true;
    }

    @Override
    public WorkInfoVo queryWorkByNo(String workNo) {
        QueryWrapper<WorkInfoVo> qw = new QueryWrapper();
        qw.eq("WORK_NO",workNo);
        WorkInfoVo workInfoVo = this.baseMapper.selectOne(qw);
        return workInfoVo;
    }

    @Override
    public List<ExecuteWorkAndMember> queryExecuteWorkAndMember() {
        return this.baseMapper.queryExecuteWorkAndMember();
    }

    @Override
    public Boolean createWorkByModel(List<WorkModelVo> pushModel) {
        List<WorkInfoVo> createList = new ArrayList<>();
        List<WorkMemberVo> createMember = new ArrayList<>();
        for (WorkModelVo workModelVo : pushModel) {
            WorkInfoVo vo = buildWorkEntityByModel(workModelVo);
            vo.setWorkStatus(WorkConstants.Status.NEW.getCode());
            createList.add(vo);
            String memberContent = workModelVo.getMemberContent();
            if (StrUtil.isNotEmpty(memberContent)) {
                List<WorkMemberVo> memberVos = JSONArray.parseArray(memberContent).stream()
                        .map(i -> buildWorkMemberByJson((JSONObject) i, vo))
                        .collect(Collectors.toList());
                createMember.addAll(memberVos);
            }
        }
        this.saveBatch(createList);
        workMemberService.saveBatch(createMember);
        return true;
    }
    /**
     * 开工调整人员出勤
     * @param workInfo
     * @return
     */
    public Boolean changeMemberCheckingByExecute(WorkInfoVo workInfo) {
        log.info("changeMemberCheckingByExecute工单开工调整考勤{}",workInfo.getWorkNo());
        //查询所有工作人员
        QueryWrapper<WorkMemberVo> qw = new QueryWrapper();
        qw.eq("WORK_NO",workInfo.getWorkNo());
        qw.eq("DELETED",0);
        List<WorkMemberVo> workMemberVos = workMemberService.getBaseMapper().selectList(qw);
        if(CollectionUtil.isEmpty(workMemberVos)){
            return true;
        }
        for (WorkMemberVo workMemberVo : workMemberVos) {
            if (StrUtil.isEmpty(workMemberVo.getWorkDays())) {
                continue;
            }
            workMemberCheckingService.changeMemberCheckingByWorkDays(workInfo,workMemberVo.getMemberId(),
                    workMemberVo.getWorkDays(),workMemberVo.getMemberName());
        }
        return true;
    }
    private void checkWorkStatus(WorkHomeUpdateDto dto, WorkInfoVo workInfoVo) {
        //申请开工，工单状态应该是待执行
        if(dto.getWorkStatus().equals(WorkConstants.Status.EXECUTE.getCode())
                &&!workInfoVo.getWorkStatus().equals(WorkConstants.Status.WAIT.getCode())){
            throw new CheckException("工单状态已改变，无法开工:"+workInfoVo.getWorkNo());
        }
        //申请完工，工单状态应该是执行中
        if(dto.getWorkStatus().equals(WorkConstants.Status.FINISH.getCode())
                &&!workInfoVo.getWorkStatus().equals(WorkConstants.Status.EXECUTE.getCode())){
            throw new CheckException("工单状态已改变，无法完工:"+workInfoVo.getWorkNo());
        }
        //申请归档，工单状态应该是完工
        if(dto.getWorkStatus().equals(WorkConstants.Status.ARCHIVE.getCode())
                &&!workInfoVo.getWorkStatus().equals(WorkConstants.Status.FINISH.getCode())){
            throw new CheckException("工单状态已改变，无法归档:"+workInfoVo.getWorkNo());
        }
        //申请取消，工单状态应该是待开工
        if(dto.getWorkStatus().equals(WorkConstants.Status.CANCEL.getCode())
                &&workInfoVo.getWorkStatus()>WorkConstants.Status.WAIT.getCode()){
            throw new CheckException("工单状态已改变，无法取消:"+workInfoVo.getWorkNo());
        }
        //申请开工，要有开工时间
        if(dto.getWorkStatus().equals(WorkConstants.Status.EXECUTE.getCode())
                &&ObjectUtil.isNull(dto.getActualStartTime())){
            throw new CheckException("开工时间缺失:"+workInfoVo.getWorkNo());
        }
        //申请完工，工单状态应该是执行中
        if(dto.getWorkStatus().equals(WorkConstants.Status.FINISH.getCode())
                &&ObjectUtil.isNull(dto.getActualEndTime())){
            throw new CheckException("完工时间缺失:"+workInfoVo.getWorkNo());
        }
        //申请归档，工单状态应该是完工
        if(dto.getWorkStatus().equals(WorkConstants.Status.ARCHIVE.getCode())
                &&ObjectUtil.isNull(dto.getWorkEvaluate())){
            throw new CheckException("归档评价确实:"+workInfoVo.getWorkNo());
        }
    }
    //待执行状态更新数据
    private WorkInfoVo updateWorkByWait(WorkInfoVo workInfoVo, WorkUpdateDto dto) {
        WorkInfoVo updateWork = new WorkInfoVo();
        updateWork.setId(workInfoVo.getId());
        //updateWork.setActualStartTime(dto.getActualStartTime());
        //updateWork.setActualEndTime(dto.getActualEndTime());
        updateWork.setWorkEvaluate(dto.getWorkEvaluate());
        return updateWork;
    }
    //创建状态更新数据
    private WorkInfoVo updateWorkByCreate(WorkInfoVo workInfoVo, WorkUpdateDto dto) {
        Assert.notEmpty(dto.getWorkContent(), "工作内容不能为空");
        Assert.notNull(dto.getWorkNature(),"工作性质不能为空");
        Assert.notNull(dto.getWorkType(),"工作类型不能为空");
        Assert.notNull(dto.getWorkLevel(),"工作等级不能为空");
        Assert.notNull(dto.getLeaderId(),"负责人不能为空");
        Assert.notEmpty(dto.getLeaderName(), "负责人不能为空");
        Assert.notNull(dto.getPlanStartTime(),"计划开始时间不能为空");
        Assert.notNull(dto.getPlanEndTime(),"计划结束时间不能为空");
        Assert.notEmpty(dto.getNormIntegral(),"工单定额总积分不能为空");
        WorkInfoVo updateWork = new WorkInfoVo();
        updateWork.setId(workInfoVo.getId());
        updateWork.setWorkContent(dto.getWorkContent());
        updateWork.setWorkNature(dto.getWorkNature());
        updateWork.setWorkType(dto.getWorkType());
        updateWork.setWorkLevel(dto.getWorkLevel());
        updateWork.setLeaderId(dto.getLeaderId());
        updateWork.setLeaderName(dto.getLeaderName());
        updateWork.setLeaderName(dto.getLeaderName());
        updateWork.setPlanStartTime(dto.getPlanStartTime());
        updateWork.setPlanEndTime(dto.getPlanEndTime());
        updateWork.setActualStartTime(dto.getActualStartTime());
        updateWork.setActualEndTime(dto.getActualEndTime());
        updateWork.setNormIntegral(dto.getNormIntegral());
        return updateWork;
    }
    private void checkWorkArchiveInfoEmpty(WorkInfoVo workInfoVo) {
        if (ObjectUtil.isEmpty(workInfoVo.getActualStartTime()) ||
                ObjectUtil.isEmpty(workInfoVo.getActualEndTime())) {
            throw new CheckException("工单实际时间缺失，无法操作:"+workInfoVo.getWorkNo());
        }
    }
    private void checkWorkInfoEmpty(WorkInfoVo workInfoVo) {
        if (ObjectUtil.isEmpty(workInfoVo.getPlanStartTime()) ||
                ObjectUtil.isEmpty(workInfoVo.getPlanEndTime())) {
            throw new CheckException("工单计划时间缺失，无法操作:"+workInfoVo.getWorkNo());
        }
        if (ObjectUtil.isEmpty(workInfoVo.getWorkContent())) {
            throw new CheckException("工单工作内容缺失，无法操作:"+workInfoVo.getWorkNo());
        }
        if (ObjectUtil.isEmpty(workInfoVo.getWorkType())
                || ObjectUtil.isEmpty(workInfoVo.getWorkNature())) {
            throw new CheckException("工单任务参数缺失，无法操作:" + workInfoVo.getWorkNo());
        }
        if (ObjectUtil.isEmpty(workInfoVo.getWorkLevel())
                || ObjectUtil.isEmpty(workInfoVo.getNormIntegral())) {
            throw new CheckException("工单任务等级积分缺失，无法操作:" + workInfoVo.getWorkNo());
        }
    }
    private WorkRecordVo buildRecord(WorkInfoVo workInfo,Integer operate,String content) {
        WorkRecordVo recordVo = new WorkRecordVo();
        recordVo.setWorkNo(workInfo.getWorkNo());
        recordVo.setWorkStatus(workInfo.getWorkStatus());
        recordVo.setOperateType(operate);
        recordVo.setCreatedBy(workInfo.getCreatedBy());
        recordVo.setCreatedTime(workInfo.getCreatedTime());
        recordVo.setOperateContent(content);
        return recordVo;
    }
    private List<WorkMemberVo> buildWorkMember(WorkInfoCreateDto dto, WorkInfoVo workInfo) {
        List<WorkListPageMemberDto> memberList = dto.getMemberList();
        List<WorkMemberVo> memberEntitys = new ArrayList();
        for (WorkListPageMemberDto memberDto : memberList) {
            WorkMemberVo entity = new WorkMemberVo();
            entity.setMemberId(memberDto.getMemberId());
            entity.setMemberName(memberDto.getMemberName());
            entity.setWorkNo(workInfo.getWorkNo());
            if(memberDto.getMemberId().equals(workInfo.getLeaderId())){
                entity.setLeaderFlag(1);
            }
            entity.setCreatedTime(workInfo.getCreatedTime());
            entity.setCreatedBy(workInfo.getCreatedBy());
            memberEntitys.add(entity);
        }
        return memberEntitys;
    }
    public static WorkInfoVo buildWorkEntity(WorkInfoCreateDto dto) {
        if (dto == null) {
            return null;
        }
        String workNo = "";
        if(dto.getWorkSource().equals(WorkConstants.Source.HAND_WORK.getCode())){
            workNo = "HW"+ RandomUtil.randomNumbers(10);
        }else if(dto.getWorkSource().equals(WorkConstants.Source.INSPECTION_WORK.getCode())){
            workNo = "IW"+ RandomUtil.randomNumbers(10);
        }else if(dto.getWorkSource().equals(WorkConstants.Source.MODE_WORK.getCode())) {
            workNo = "MW" + RandomUtil.randomNumbers(10);
        }else if(dto.getWorkSource().equals(WorkConstants.Source.SUPERIOR_WORK.getCode())) {
            workNo = "SW" + RandomUtil.randomNumbers(10);
        }
        WorkInfoVo workInfoVo = new WorkInfoVo();
        workInfoVo.setWorkNo(workNo);
        workInfoVo.setWorkSource(dto.getWorkSource());
        workInfoVo.setModelNo(dto.getModelNo());
        workInfoVo.setWorkContent(dto.getWorkContent());
        workInfoVo.setWorkNature(dto.getWorkNature());
        workInfoVo.setWorkType(dto.getWorkType());
        workInfoVo.setWorkLevel(dto.getWorkLevel());
        workInfoVo.setLeaderId(dto.getLeaderId());
        workInfoVo.setLeaderName(dto.getLeaderName());
        workInfoVo.setPlanStartTime(dto.getPlanStartTime());
        workInfoVo.setPlanEndTime(dto.getPlanEndTime());
        workInfoVo.setNormIntegral(dto.getNormIntegral());
        workInfoVo.setTeamId(dto.getTeamId());
        workInfoVo.setStationId(dto.getStationId());
        return workInfoVo;
    }
    public static WorkInfoVo buildWorkEntityByModel(WorkModelVo workModelVo) {
        if (workModelVo == null) {
            return null;
        }
        String workNo = "MW" + RandomUtil.randomNumbers(10);

        WorkInfoVo workInfoVo = new WorkInfoVo();
        workInfoVo.setWorkNo(workNo);
        workInfoVo.setWorkSource(WorkConstants.Source.MODE_WORK.getCode());
        workInfoVo.setModelNo(workModelVo.getModelNo());
        workInfoVo.setWorkContent(workModelVo.getWorkContent());
        workInfoVo.setWorkType(workModelVo.getWorkType());
        workInfoVo.setWorkNature(workModelVo.getWorkNature());
        workInfoVo.setWorkLevel(workModelVo.getWorkLevel());
        workInfoVo.setLeaderId(workModelVo.getLeaderId());
        workInfoVo.setLeaderName(workModelVo.getLeaderName());
        workInfoVo.setNormIntegral(workModelVo.getNormIntegral());
        workInfoVo.setStationId(workModelVo.getStationId());
        workInfoVo.setTeamId(workModelVo.getTeamId());
        workInfoVo.setCreatedBy("job");
        workInfoVo.setCreatedTime(new Date());
        return workInfoVo;
    }
    private WorkMemberVo buildWorkMemberByJson(JSONObject json, WorkInfoVo workInfo) {
        WorkMemberVo entity = new WorkMemberVo();
        entity.setMemberId(json.getString("memberId"));
        entity.setMemberName(json.getString("memberName"));
        entity.setWorkNo(workInfo.getWorkNo());
        if(entity.getMemberId()==workInfo.getLeaderId()){
            entity.setLeaderFlag(1);
        }
        entity.setCreatedTime(workInfo.getCreatedTime());
        entity.setCreatedBy(workInfo.getCreatedBy());
        return entity;
    }

}
