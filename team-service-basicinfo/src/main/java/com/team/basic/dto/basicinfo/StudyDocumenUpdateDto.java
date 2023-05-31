package com.team.basic.dto.basicinfo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value = "",description = "修改学习文件")
public class StudyDocumenUpdateDto  {

    @ApiModelProperty("文件id")
    private Integer documentId;
    @ApiModelProperty("文件名称")
    private String documentName;
    @ApiModelProperty("标签")
    private String label;
    @ApiModelProperty("学习人员")
    private List<Map<String,String>> studyPerson;
    @ApiModelProperty("学习要求")
    private String requirement;
    @ApiModelProperty("备注")
    private String remark;


}



