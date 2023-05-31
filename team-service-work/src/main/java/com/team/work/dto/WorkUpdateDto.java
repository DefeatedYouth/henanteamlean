package com.team.work.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author QianT
 * @date 2022/7/30$
 */
@Data
public class WorkUpdateDto {
    private Integer id;
    private String workContent;
    private Integer workNature;
    private Integer workType;
    private Integer workLevel;
    private String leaderId;
    private String leaderName;
    private Date planStartTime;
    private Date planEndTime;
    private Date actualStartTime;
    private Date actualEndTime;
    private String normIntegral;
    private Integer workEvaluate;
    private List<WorkListPageMemberDto> memberList;
}
