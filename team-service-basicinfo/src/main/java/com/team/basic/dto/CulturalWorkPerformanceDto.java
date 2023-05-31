package com.team.basic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CulturalWorkPerformanceDto {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "删除ID")
    private List<Integer> deleteIdList;

    @ApiModelProperty(value = "业绩名称")
    private String performanceName;

    @ApiModelProperty(value = "主要工作内容或重大项目内容")
    private String workContent;

    @ApiModelProperty(value = "人员ID")
    private String personnelId;

    @ApiModelProperty(value = "人员")
    private String personnelName;

    @ApiModelProperty(value = "证明材料ID")
    private String supportingMaterialsId;

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
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "当前页数")
    private Integer pageNum;

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "是否删除（1:已删除 0:未删除）")
    private Integer is_delete;
}
