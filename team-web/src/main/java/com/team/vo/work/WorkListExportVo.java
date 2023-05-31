package com.team.vo.work;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
public class WorkListExportVo {
    //工单序号
    private int no;
    private String workNo;
    //工作内容
    private String workContent;
    //工作类型(0班组建设、1员工培训、2安全活动、3现场生产、4基建验收、5基础管理、6科技创新、7综合事务、8临时任务、9其他)
    private String workType;
    //工作性质(0出勤、1出差、2培训、3借调)
    private String workNature;
    //计划开始时间yyyy-MM-dd
    private String planStartTime;
    //计划结束时间yyyy-MM-dd
    private String planEndTime;
    //负责人ID
    private String leaderId;
    //负责人名称
    private String leaderName;
    //工单人员信息
    private String memberNames;
    //定额积分
    private String normIntegral;
    //工单人员总积分
    private String memberTotalIntegral;
    //工单人员最终积分
    private String memberFinalIntegral;
    //工作等级(0一般任务、1紧急任务、2重要任务)
    private String workLevel;
    //工作状态0新建、1待执行、2执行中、3已完成、4已归档、5已取消
    private String workStatus;
    //工作评价0达标、1未达标、2超预期
    private String workEvaluate;
    //实际开始时间yyyy-MM-dd
    private String actualStartTime;
    //实际结束时间yyyy-MM-dd
    private String actualEndTime;
    //备注
    private String remark;
}
