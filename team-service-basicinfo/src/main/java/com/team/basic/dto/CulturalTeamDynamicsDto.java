package com.team.basic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
public class CulturalTeamDynamicsDto {


    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "图片主键ID")
    private List<Integer> fileIdList;

    @ApiModelProperty(value = "删除ID")
    private List<Integer> deleteIdList;

    @ApiModelProperty(value = "班组动态标题")
    private String title;

    @ApiModelProperty(value = "班组动态描述")
    private String dynamicDescription;

    @ApiModelProperty(value = "班组动态图片路径")
    private List<String> picturePathList;


    @ApiModelProperty(value = "班组动态图片ID")
    private String pictureId;

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

    @ApiModelProperty(value = "类型")
    private String type;

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
