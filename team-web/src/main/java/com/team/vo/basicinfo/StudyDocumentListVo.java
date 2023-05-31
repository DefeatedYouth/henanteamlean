package com.team.vo.basicinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.form.basicinfo.StudyDocumenCreatForm;
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
@ApiModel(value = "" ,description = "学习文件列表展示")
public class StudyDocumentListVo {
    @ApiModelProperty("文件id")
    private Integer documentId;
    @ApiModelProperty("文件名称")
    private String documentName;
    @ApiModelProperty("标签")
    private String label;
    @ApiModelProperty("学习要求")
    private String requirement;
    @ApiModelProperty("已打卡")
    private String alreadyStudy;
    @ApiModelProperty("状态")
    private Integer state;
    @ApiModelProperty("发布日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseDate;
    @ApiModelProperty("附件")
    private String accessory;
    @ApiModelProperty("备注")
    private String remark;

}
