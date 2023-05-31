package com.team.form.work;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 工单人员查询
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单人员查询--人员变动信息维护页")
public class WorkMemberQueryForm extends BaseRequest {
    @ApiModelProperty("工单编码")
    private String workNo;

    @Override
    public boolean verifyParam() {
        Assert.notEmpty(workNo, "工单编码不能为空");
        return true;
    }
}
