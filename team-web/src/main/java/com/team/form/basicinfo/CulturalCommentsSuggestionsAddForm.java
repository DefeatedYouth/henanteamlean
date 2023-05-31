package com.team.form.basicinfo;

import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CulturalCommentsSuggestionsAddForm extends BaseRequest {

    @ApiModelProperty(value = "提议类型")
    private String proposalType;

    @ApiModelProperty(value = "附议人ID （多个）")
    private List<String> secondersId;

    @ApiModelProperty(value = "附议人（多个）")
    private List<String> secondersName;

    @ApiModelProperty(value = "提议主题")
    private String proposedTheme;

    @ApiModelProperty(value = "是否采纳（0：否 ，1：是）")
    private Integer adopted;

    @ApiModelProperty(value = "采纳/不采纳 原因")
    private String reason;

    @ApiModelProperty(value = "采纳部门")
    private String department;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "发布状态（0：未发布，1：已发布）")
    private Integer releaseStatus;

    @Override
    public boolean verifyParam() {
        return true;
    }
}
