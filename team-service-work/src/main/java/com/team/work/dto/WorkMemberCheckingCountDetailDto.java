package com.team.work.dto;

import lombok.Data;

import java.util.List;


/**
 * @author QianT
 * @date 2022/8/1$
 */
@Data
public class WorkMemberCheckingCountDetailDto {
    private Integer memberNum;
    private Integer workNum;
    private List<PersonPlanDto> personPlans;
    private List<WorkPlanDto> workPlans;
}
