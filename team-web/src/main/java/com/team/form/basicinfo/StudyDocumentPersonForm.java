package com.team.form.basicinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value = "",description = "学习文件相关人员")
public class StudyDocumentPersonForm {
    @ApiModelProperty("人员id")
    private String personId;
    @ApiModelProperty("人员名称")
    private String personName;
}
