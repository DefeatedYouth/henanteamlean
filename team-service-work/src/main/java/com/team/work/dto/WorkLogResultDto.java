package com.team.work.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

/**
 * @author QianT
 * @date 2022/8/2$
 */
@Data
public class WorkLogResultDto {
    private String teamName;
    private Integer memberId;
    private String memberName;
    private String erpNo;
    private String remark;
    private List<WorkLogDayInfoDto> dayInfos;
}
