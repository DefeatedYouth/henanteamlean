package com.team.work.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author QianT
 * @date 2022/7/29$
 */
@Data
public class WorkInfoCreateDto {
    private Integer workSource;
    private String modelNo;
    private String workContent;
    private Integer workNature;
    private Integer workType;
    private Integer workLevel;
    private String leaderId;
    private String leaderName;
    private Date planStartTime;
    private Date planEndTime;
    private String normIntegral;
    private Integer teamId;
    private Integer stationId;
    private List<WorkListPageMemberDto> memberList;
}
