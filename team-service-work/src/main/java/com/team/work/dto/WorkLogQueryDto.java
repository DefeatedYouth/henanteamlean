package com.team.work.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author QianT
 * @date 2022/8/2$
 */
@Data
public class WorkLogQueryDto {
    private String teamId;
    private String stationId;
    private Integer endTime;
    private Integer startTime;
}
