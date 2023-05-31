package com.team.form.performance.monitorModel;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 删除班长绩效对象信息
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="删除班长绩效对象信息",description="删除班长绩效对象信息")
public class DeleteMonitorPerforModelForm extends BaseRequest {

    @ApiModelProperty("绩效模板id")
    private Integer id;

    /**
     *  修改人名称
     */
    @ApiModelProperty(value = "用户名称", name = "updatedBy")
    private String updatedBy;

    @Override
    public boolean verifyParam() {
        Assert.notNull(id, "绩效模板id不能为空");
        Assert.notEmpty(updatedBy, "用户名称不能为空");
        return true;
    }
}
