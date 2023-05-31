package com.team.vo.basicinfo;

import com.team.basic.entity.FilePathVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("班组动态查询")
public class CulturalTeamDynamicsQueryVo {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "图片文件相关信息")
    private List<FilePathQueryVo> filePathQueryVoList;

    @ApiModelProperty(value = "班组动态标题")
    private String title;

    @ApiModelProperty(value = "班组动态描述")
    private String dynamicDescription;

    @ApiModelProperty(value = "创建人ID")
    private String createdId;

    @ApiModelProperty(value = "创建人（即发布人）")
    private String createdBy;

    @ApiModelProperty(value = "创建时间（即发布时间）")
    private String createdTime;
}
