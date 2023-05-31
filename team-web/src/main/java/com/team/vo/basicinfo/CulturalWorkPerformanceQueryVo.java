package com.team.vo.basicinfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CulturalWorkPerformanceQueryVo {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "证明材料文件相关信息")
    private List<FilePathQueryVo> filePathQueryVoList;

    @ApiModelProperty(value = "业绩名称")
    private String performanceName;

    @ApiModelProperty(value = "主要工作内容或重大项目内容")
    private String workContent;

    @ApiModelProperty(value = "证明材料ID")
    private String supportingMaterialsId;

    @ApiModelProperty(value = "人员ID")
    private String personnelId;

    @ApiModelProperty(value = "人员")
    private String personnelName;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
