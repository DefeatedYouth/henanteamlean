package com.team.work.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author QianT
 * @date 2022/7/29$
 */
@Data
public class WorkListPageDto {
    private Integer pageSize;
    private Integer pageNum;
    private String teamId;
    private Integer workNature;
    private Integer workType;
    private Integer workLevel;
    private Integer workStatus;
    private Integer workEvaluate;
    private String workContent;
    private Date planStartTime;
    private Date planEndTime;
    private String leaderName;
}
