package com.team.vo.basicinfo;

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
@ApiModel(value = "班组简介展示")
public class TeamAboutShowVo  {

    @ApiModelProperty("班组名称")
    private String teamName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("成立时间")
    private Date formed;
    @ApiModelProperty("班组人数")
    private Integer teamSize;
    @ApiModelProperty("班级荣誉")
    private List<TeamAboutHonorInfoVo> teamHonor;
    @ApiModelProperty("班组职责")
    private String teamDuty;
    @ApiModelProperty("所辖站")
    private List<TeamAboutStationInfoVo> theStation;

    @ApiModelProperty("交流500kV站数量")
    private Integer number500kV;
    @ApiModelProperty("交流220kV站数量")
    private Integer number220kV;
    @ApiModelProperty("交流110kV站数量")
    private Integer number110kV;
    @ApiModelProperty("交流35kV站数量")
    private Integer number35kV;
}
