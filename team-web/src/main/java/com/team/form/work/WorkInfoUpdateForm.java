package com.team.form.work;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 非运检工单修改
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单修改")
public class WorkInfoUpdateForm extends BaseRequest {
    @ApiModelProperty("工单ID")
    private Integer id;
    @ApiModelProperty("工作内容")
    private String workContent;
    @ApiModelProperty("工作性质(0出勤、1出差、2培训、3借调)")
    private Integer workNature;
    @ApiModelProperty("工作类型(0班组建设、1员工培训、2安全活动、3现场生产、4基建验收、5基础管理、6科技创新、7综合事务、8临时任务、9其他)")
    private Integer workType;
    @ApiModelProperty("工作等级(0一般任务、1紧急任务、2重要任务)")
    private Integer workLevel;
    @ApiModelProperty("负责人名称")
    private String leaderId;
    @ApiModelProperty("负责人名称")
    private String leaderName;
    @ApiModelProperty("计划开始时间yyyy-MM-dd")
    //@DateTimeFormat(fallbackPatterns = {"yyyy-MM-dd","yyyy/MM/dd"})
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date planStartTime;
    @ApiModelProperty("计划结束时间yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date planEndTime;
    @ApiModelProperty("实际开始时间yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualStartTime;
    @ApiModelProperty("实际结束时间yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualEndTime;
    @ApiModelProperty("工单定额总积分")
    private String normIntegral;
    @ApiModelProperty("工作评价0达标、1未达标、2超预期")
    private Integer workEvaluate;
    @ApiModelProperty("工作人员名称")
    private List<WorkListPageMemberForm> memberList;

    @Override
    public boolean verifyParam() {
        Assert.notNull(id, "工单ID不能为空");
        return true;
    }
}
