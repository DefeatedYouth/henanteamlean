package com.team.work.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author QianT
 * @date 2022/8/3$
 */
@Data
public class WorkVacateCreateDto {
    private String memberId;
    private String memberName;
    private Integer leaveType;
    private String startTime;
    private String endTime;
    private String remark;
}
