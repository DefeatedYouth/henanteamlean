package com.team.performance.quatz;

import com.team.performance.dao.PerformanceEvaluationModelMapper;
import com.team.performance.dao.PerformancePublishedInfoMapper;
import com.team.performance.dao.PerformancePublishedTeamrelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 绩效发布定时任务
 *
 * @Author: xb
 * @Date: 2022/5/30 16:50
 */
@Slf4j
//@EnableScheduling
//@Component
//@Configuration
public class PublishPerformanceExcuteJob {

    @Resource
    PerformancePublishedInfoMapper performancePublishedInfoMapper;

    @Resource
    PerformancePublishedTeamrelMapper performancePublishedTeamrelMapper;

    @Resource
    PerformanceEvaluationModelMapper performanceEvaluationModelMapper;

    // 每个月初一号00：00：00
    @Scheduled(cron = "0 0 0 1 * ? *")
    public void execute()
    {
        log.debug("quatz_job PublishPerformanceExcuteJob is start");
        try {
            // 查询已发布的；当前时间大于发布时间的绩效模板；且适用班组为部分班组的；且未删除的绩效
            // 并先迁移主表至发布表中，并塞入时间
            performancePublishedInfoMapper.insertPublishedFromModel();

            // 再迁移关系表至发布关系表中
            performancePublishedTeamrelMapper.insetPublishedTeamFromMpdelTeam();

            // 修改发布关系表中的部分字段
            performancePublishedTeamrelMapper.updatePublishedTeam();

            // 修改模板表中发布状态为已发布状态
            performanceEvaluationModelMapper.updateModelPublishState();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.debug("quatz_job PublishPerformanceExcuteJob is end");
    }
}
