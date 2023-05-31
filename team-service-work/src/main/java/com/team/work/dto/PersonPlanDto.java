package com.team.work.dto;

import lombok.Data;

import java.util.List;

/**
 * @author QianT
 * @date 2022/8/1$
 */
@Data
public class PersonPlanDto {
    private Integer type;
    private Integer personCount;
    private Integer dayCount;
    private String memberId;
}
