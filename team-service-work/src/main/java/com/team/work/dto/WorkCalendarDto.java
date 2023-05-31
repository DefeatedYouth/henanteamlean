package com.team.work.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author QianT
 * @date 2022/8/1$
 */
@Data
public class WorkCalendarDto {
    private Integer dayAt;
    private Integer onWorkCount;
    private Integer onTripCount;
    private Integer onLeaveCount;
    private List<WorkCalendarPlanDto> workPlans;
}
