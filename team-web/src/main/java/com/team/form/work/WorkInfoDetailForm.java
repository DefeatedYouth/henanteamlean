package com.team.form.work;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 工单详情
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单详情")
public class WorkInfoDetailForm extends BaseRequest {
    @ApiModelProperty("工单ID")
    private Integer id;

    @Override
    public boolean verifyParam() {
        Assert.notNull(id, "工单ID不能为空");
        return true;
    }
}
