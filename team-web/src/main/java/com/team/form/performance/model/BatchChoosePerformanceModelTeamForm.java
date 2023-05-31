package com.team.form.performance.model;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 批量选择适用班组
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="批量选择适用班组",description="批量选择适用班组")
public class BatchChoosePerformanceModelTeamForm extends BaseRequest {

    @ApiModelProperty("绩效模板id")
    private Integer modelId;

    @ApiModelProperty("班组编码(,隔开)")
    private String teamCodes;

    @ApiModelProperty("班组名称(,隔开)")
    private String teamNames;

    /**
     * 更新人
     */
    @ApiModelProperty("用户名称")
    private String updatedBy;

    @Override
    public boolean verifyParam() {
        Assert.notNull(modelId, "绩效模板id不能为空");
        Assert.notEmpty(teamCodes, "绩效适用班组编码不能为空");
        Assert.notEmpty(teamNames, "绩效适用班组名称不能为空");
        Assert.notEmpty(updatedBy, "用户名称不能为空");
        return true;
    }
}
