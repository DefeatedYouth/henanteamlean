package com.team.form.basicinfo;

import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CulturalCommentsSuggestionsQueryForm extends BaseRequest {

    @ApiModelProperty(value = "提议主题")
    private String proposedTheme;

    @ApiModelProperty(value = "是否采纳（0：否 ，1：是）")
    private Integer adopted;

    @ApiModelProperty(value = "提议类型")
    private String proposalType;

    @ApiModelProperty(value = "创建人ID（即提出人ID）")
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
