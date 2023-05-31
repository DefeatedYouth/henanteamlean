package com.team.form.basicinfo;

import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CulturalWorkPerformanceUpdateForm extends BaseRequest {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "业绩名称")
    private String performanceName;

    @ApiModelProperty(value = "主要工作内容或重大项目内容")
    private String workContent;

    @ApiModelProperty(value = "人员ID")
    private String personnelId;

    @ApiModelProperty(value = "人员")
    private String personnelName;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "发布状态（0：未发布，1：已发布）")
    private Integer releaseStatus;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @Override
    public boolean verifyParam() {
        return true;
    }
}
