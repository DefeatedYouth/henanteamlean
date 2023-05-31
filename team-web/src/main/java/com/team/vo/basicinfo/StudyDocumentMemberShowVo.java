package com.team.vo.basicinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value = "" ,description = "班长学习展示")
public class StudyDocumentMemberShowVo {
    @ApiModelProperty("文件id")
    private Integer documentId;
    @ApiModelProperty("文件名称")
    private String documentName;
    @ApiModelProperty("是否打卡")
    private Integer isStudy;
    @ApiModelProperty("发布日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseDate;
}
