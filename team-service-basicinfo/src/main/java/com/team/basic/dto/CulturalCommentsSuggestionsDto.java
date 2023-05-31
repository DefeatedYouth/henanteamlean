package com.team.basic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
public class CulturalCommentsSuggestionsDto {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "删除ID")
    private List<Integer> deleteIdList;

    @ApiModelProperty(value = "提议类型")
    private String proposalType;

    @ApiModelProperty(value = "附议人ID （多个）")
    private List<String> secondersId;

    @ApiModelProperty(value = "附议人（多个）")
    private List<String> secondersName;

    @ApiModelProperty(value = "附议人ID")
    private String seconderId;

    @ApiModelProperty(value = "附议人")
    private String seconderName;

    @ApiModelProperty(value = "提议主题")
    private String proposedTheme;

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

    @ApiModelProperty(value = "发布状态（0：未发布，1：已发布）")
    private Integer releaseStatus;

    @ApiModelProperty(value = "创建人ID")
    private String createdId;

    @ApiModelProperty(value = "创建人（即发布人）")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新人ID")
    private String updatedId;

    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "当前页数")
    private Integer pageNum;

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "是否删除（1:已删除 0:未删除）")
    private Integer is_delete;
}
