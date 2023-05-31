package com.team.form.basicinfo;

import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CulturalWorkPerformanceQueryForm extends BaseRequest {

    @ApiModelProperty(value = "业绩名称")
    private String performanceName;

    @ApiModelProperty(value = "人员")
    private String personnelName;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "当前页数")
    private Integer pageNum;

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    @Override
    public boolean verifyParam() {
        return true;
    }
}
