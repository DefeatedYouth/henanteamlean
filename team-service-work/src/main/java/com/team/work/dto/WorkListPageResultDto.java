package com.team.work.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author QianT
 * @date 2022/7/29$
 */
@Data
public class WorkListPageResultDto {
    private Integer id;
    private String workNo;
    private String workContent;
    private Integer workType;
    private Integer workNature;
    private Date planStartTime;
    private Date planEndTime;
    private String leaderId;
    private String leaderName;
    private List<WorkListPageMemberDto> memberVoList;
    private Integer workLevel;
    private Integer workStatus;
    private Integer workEvaluate;
    private Date actualStartTime;
    private Date actualEndTime;
    private String remark;
    private String normIntegral;
}
