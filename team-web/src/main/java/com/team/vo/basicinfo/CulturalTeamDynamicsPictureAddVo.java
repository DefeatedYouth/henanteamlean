package com.team.vo.basicinfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CulturalTeamDynamicsPictureAddVo {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "文件路径")
    private String path;
}
