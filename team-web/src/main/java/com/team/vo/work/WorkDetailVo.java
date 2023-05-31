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
@ApiModel(value="",description="工单详情列表")
public class WorkDetailVo {
    @ApiModelProperty("工单ID")
    private Integer id;
    @ApiModelProperty("工作内容")
    private String workContent;
    @ApiModelProperty("工作类型(0班组建设、1员工培训、2安全活动、3现场生产、4基建验收、5基础管理、6科技创新、7综合事务、8临时任务、9其他)")
    private Integer workType;
    @ApiModelProperty("工作性质(0出勤、1出差、2培训、3借调)")
    private Integer workNature;
    @ApiModelProperty("计划开始时间yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date planStartTime;
    @ApiModelProperty("计划结束时间yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date planEndTime;
    @ApiModelProperty("负责人ID")
    private String leaderId;
    @ApiModelProperty("负责人名称")
    private String leaderName;
    @ApiModelProperty("工单人员信息")
    private List<WorkListPageMemberVo> memberVoList;
    @ApiModelProperty("工作等级(0一般任务、1紧急任务、2重要任务)")
    private Integer workLevel;
    @ApiModelProperty("工作状态0新建、1待执行、2执行中、3已完成、4已归档、5已取消")
    private Integer workStatus;
    @ApiModelProperty("工作评价0达标、1未达标、2超预期")
    private Integer workEvaluate;
    @ApiModelProperty("实际开始时间yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date actualStartTime;
    @ApiModelProperty("实际结束时间yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date actualEndTime;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("工单定额总积分")
    private String normIntegral;
}
