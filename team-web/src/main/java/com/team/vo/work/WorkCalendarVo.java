package com.team.vo.work;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单日历列表")
public class WorkCalendarVo {
    @ApiModelProperty("天:20220502")
    private Integer dayAt;
    @ApiModelProperty("出勤人数")
    private Integer onWorkCount;
    @ApiModelProperty("出差人数")
    private Integer onTripCount;
    @ApiModelProperty("休假人数")
    private Integer onLeaveCount;
    @ApiModelProperty("工作单")
    private List<WorkPlan> workPlans;

    @Data
    @ApiModel(value="",description="工单日历:工作单")
    public static class WorkPlan{
        @ApiModelProperty("工单类型")
        private Integer workType;
        @ApiModelProperty("工单数量")
        private Integer workNum;
        @ApiModelProperty("工单人员")
        private String workMember;
    }

}
