package com.team.form.basicinfo;

import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("班组动态添加")
public class CulturalTeamDynamicsAddForm extends BaseRequest {


    @ApiModelProperty(value = "班组动态标题")
    private String title;

    @ApiModelProperty(value = "班组动态描述")
    private String dynamicDescription;

    @ApiModelProperty(value = "图片主键ID")
    private List<Integer> fileIdList;

    @ApiModelProperty(value = "发布状态（0：未发布，1：已发布）")
    private Integer releaseStatus;

    @Override
    public boolean verifyParam() {
        return true;
    }
}
