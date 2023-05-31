package com.team.basic.dto.basicinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value="",description="荣誉称号信息")
public  class TeamAboutHonorInfoDto {
    @ApiModelProperty("荣誉称号id")
    private Integer honorId;
    @ApiModelProperty("荣誉称号名称")
    private String honorName;
}


