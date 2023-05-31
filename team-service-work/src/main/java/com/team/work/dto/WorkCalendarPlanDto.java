package com.team.work.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author QianT
 * @date 2022/8/1$
 */
@Data
public class WorkCalendarPlanDto {
    private Integer workDay;
    private Integer workType;
    private Integer workNum;
    private String workName;
    private String workMember;
}
