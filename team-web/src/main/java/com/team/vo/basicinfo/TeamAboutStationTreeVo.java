package com.team.vo.basicinfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Date: 2022/7/29 15:55
 * @Version: 1.0
 */
@Data
public class TeamAboutStationTreeVo {
/*    @ApiModelProperty(value = "流水号ID")
    private String id;*/

    @ApiModelProperty(value = "ID")
    private String ids;

    @ApiModelProperty(value = "名称")
    private String name;

/*    @ApiModelProperty(value = "父节点ID")
    private String pid;*/

    @ApiModelProperty(value = "节点层级")
    private String level;

    @ApiModelProperty(value = "子节点集合")
    private List<TeamAboutStationTreeVo> children;
}
