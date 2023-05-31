package com.team.quatz;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.team.work.dto.ExecuteWorkAndMember;
import com.team.work.entity.WorkMemberCheckingVo;
import com.team.work.entity.WorkModelVo;
import com.team.work.service.WorkInfoService;
import com.team.work.service.WorkMemberCheckingService;
import com.team.work.service.WorkModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 工单相关任务类
 * @author QianT
 * @date 2022/8/4$
 */
@Component
@Slf4j
public class WorkInfoJob {
    @Autowired
    private WorkInfoService workInfoService;

    @Autowired
    private WorkMemberCheckingService workMemberCheckingService;

    @Autowired
    private WorkModelService workModelService;

    /**
     * 人员考勤任务
     * 每天1点执行
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void memberCheckingJob() {
        log.info("memberCheckingJob任务开始--------");
        String today = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
        //判断当天是否是假日

        //判断当天是否是周六周日
        int dayIndex = DateUtil.dayOfWeek(new Date());
        if(dayIndex==1||dayIndex==6){
            log.info("memberCheckingJob假期不执行任务{}",dayIndex);
            return ;
        }

        //查询所有执行中的工单以及工单人员
        List<ExecuteWorkAndMember> memberList = workInfoService.queryExecuteWorkAndMember();
        //筛选出需要新增考勤的人员
        List<ExecuteWorkAndMember> workCheckingList = memberList.stream()
                .filter(i -> i.getWorkDays().contains(today))
                .collect(Collectors.toList());

        //新增工单考勤
        List<WorkMemberCheckingVo> jobCheckingList = workCheckingList.stream().map(i -> {
            return buildMemberChecking(i,today);
        }).collect(Collectors.toList());

        workMemberCheckingService.saveBatch(jobCheckingList);

        //查询所有人的信息 fixme

        //查询所有已有假勤的人员
        List<WorkMemberCheckingVo> absenceList = workMemberCheckingService.queryAbsenceList(today);

        //过滤出已有工单已有假勤的人员，

        //新增默认考勤


        log.info("memberCheckingJob任务结束--------");
    }

    /**
     * 模板导入任务
     * 每天2点执行
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void modelImport(){
        log.info("modelImport任务开始--------");
        //这周的天数
        int dayOfMonth = DateUtil.dayOfMonth(new Date());
        int dayOfWeek = DateUtil.dayOfWeek(new Date());

        //查所有可推送的模板
        List<WorkModelVo> modelVos = workModelService.queryPushModel();
        List<WorkModelVo> pushModel = modelVos.stream().filter(i -> {
            if (i.getPeriodType() == 0) {
                return i.getPeriod() == dayOfWeek;
            } else if (i.getPeriodType() == 1) {
                return i.getPeriod() == dayOfMonth;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        log.info("modelImport需推送的模板{}",pushModel.size());

        Boolean b = workInfoService.createWorkByModel(pushModel);
        log.info("modelImport创建结果{}",b);
        log.info("modelImport任务结束--------");
    }

    private WorkMemberCheckingVo buildMemberChecking(ExecuteWorkAndMember i, String today) {
        WorkMemberCheckingVo vo = new WorkMemberCheckingVo();
        vo.setMemberId(i.getMemberId());
        vo.setMemberName(i.getMemberName());
        vo.setWorkNo(i.getWorkNo());
        vo.setWorkDay(Integer.valueOf(today));
        vo.setWorkType(i.getWorkType());
        vo.setWorkStatus(i.getWorkNature());
        vo.setCreatedTime(new Date());
        vo.setCreatedBy("job");
        return vo;
    }
}
