package com.team.basic.dto.basicinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value = "",description = "班组简介维护")
public class TeamAboutEditDto  {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("成立时间")
    private Date formed;
    @ApiModelProperty("班级荣誉id")
    private List<TeamAboutHonorInfoDto> teamHonor;
    @ApiModelProperty("班组职责")
    private String teamDuty;
    @ApiModelProperty("所辖站id")
    private List<TeamAboutStationInfoDto> theStation;

}
