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
@ApiModel(value="",description="工单日历总计")
public class WorkCalendarCountDetailVo {
    @ApiModelProperty("人数")
    private Integer memberNum;
    @ApiModelProperty("任务数量")
    private Integer workNum;
    @ApiModelProperty("人员出勤")
    private List<PersonPlan> personPlans;
    @ApiModelProperty("工作单")
    private List<WorkPlan> workPlans;

    @Data
    @ApiModel(value="",description="工单日历总计:人员出勤")
    public static class PersonPlan{
        @ApiModelProperty("出勤类型0出勤、1出差培训、4休假")
        private Integer type;
        @ApiModelProperty("出勤人次")
        private Integer personCount;
        @ApiModelProperty("出勤天次")
        private Integer dayCount;
    }
    @Data
    @ApiModel(value="",description="工单日历总计:工作单")
    public static class WorkPlan{
        @ApiModelProperty("工单类型0班组建设、1员工培训、2安全活动、3现场生产、4基建验收、5基础管理、6科技创新、7综合事务、8临时任务、9其他")
        private Integer workType;
        @ApiModelProperty("工单数量")
        private Integer workNum;
    }

}
