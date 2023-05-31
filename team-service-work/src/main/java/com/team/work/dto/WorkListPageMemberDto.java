package com.team.work.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
public class WorkListPageMemberDto {

    private String memberId;

    private String memberName;

    private String totalIntegral;

    private String finalIntegral;
}
