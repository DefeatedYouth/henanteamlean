package com.team.vo.work;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单模板信息")
public class WorkModelListPageVo {
    @ApiModelProperty("模型ID")
    private Integer id;
    @ApiModelProperty("模型No")
    private String modelNo;
    @ApiModelProperty("工作内容")
    private String workContent;
    @ApiModelProperty("工作性质(0出勤、1出差、2培训、3借调)")
    private Integer workNature;
    @ApiModelProperty("工作类型(0班组建设、1员工培训、2安全活动、3现场生产、4基建验收、5基础管理、6科技创新、7综合事务、8临时任务、9其他)")
    private Integer workType;
    @ApiModelProperty("工作等级(0一般任务、1紧急任务、2重要任务)")
    private Integer workLevel;
    @ApiModelProperty("负责人ID")
    private String leaderId;
    @ApiModelProperty("负责人名称")
    private String leaderName;
    @ApiModelProperty("工单人员信息json{'memberId: 1,memberName: zhangsan'}")
    private String memberContent;
    @ApiModelProperty("工单定额总积分")
    private String normIntegral;
    @ApiModelProperty("周期")
    private Integer period;
    @ApiModelProperty("周期单位(0天,1月)")
    private Integer periodType;
    @ApiModelProperty("周期推送状态(0关,1开)")
    private Integer periodStatus;

}
