package com.team.form.basicinfo;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("班组动态更新")
public class CulturalTeamDynamicsUpdateForm extends BaseRequest {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "班组动态标题")
    private String title;

    @ApiModelProperty(value = "班组动态描述")
    private String dynamicDescription;

    @ApiModelProperty(value = "发布状态（0：未发布，1：已发布）")
    private Integer releaseStatus;

    @Override
    public boolean verifyParam() {
        return true;
    }
}
