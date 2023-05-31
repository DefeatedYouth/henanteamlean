package com.team.vo.basicinfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.util.List;

@Data
public class CulturalCommentsSuggestionsQueryVo {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "附件文件相关信息")
    private List<FilePathQueryVo> filePathQueryVoList;

    @ApiModelProperty(value = "提议主题")
    private String proposedTheme;

    @ApiModelProperty(value = "附议人ID")
    private String seconderId;

    @ApiModelProperty(value = "附议人")
    private String seconderName;

    @ApiModelProperty(value = "是否采纳（0：否 ，1：是）")
    private Integer adopted;

    @ApiModelProperty(value = "采纳/不采纳 原因")
    private String reason;

    @ApiModelProperty(value = "采纳部门")
    private String department;

    @ApiModelProperty(value = "附件关联ID")
    private String enclosureId;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "创建人ID")
    private String createdId;

    @ApiModelProperty(value = "创建人（即发布人）")
    private String createdBy;

    @ApiModelProperty(value = "创建时间（即发布时间）")
    private String createdTime;
}
