package com.team.form.basicinfo;

import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("班组动态查询")
public class CulturalTeamDynamicsQueryForm extends BaseRequest {

    @ApiModelProperty(value = "班组动态标题")
    private String title;

    @ApiModelProperty(value = "创建人ID")
    private String createdId;

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

