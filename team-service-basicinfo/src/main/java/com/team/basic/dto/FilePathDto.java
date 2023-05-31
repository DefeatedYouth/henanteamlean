package com.team.basic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FilePathDto {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "文件路径")
    private String path;

    @ApiModelProperty(value = "文件路径ID")
    private String pathId;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "是否删除（1:已删除 0:未删除）")
    private Integer is_delete;
}
