package com.team.work.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author QianT
 * @date 2022/7/30$
 */
@Data
public class WorkEvaluateUpdateDto {
    private String workNo;
    private Integer id;
    private Date actualStartTime;
    private Date actualEndTime;
    private Integer workEvaluate;
    private String totalIntegral;
    private String adjustIntegral;
    private String finalIntegral;
}
