package com.team.vo.basicinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value="",description="荣誉称号信息")
public  class TeamAboutStationInfoVo {
    @ApiModelProperty("变电站id")
    private String stationId;
    @ApiModelProperty("变电站名称")
    private String stationName;
}


