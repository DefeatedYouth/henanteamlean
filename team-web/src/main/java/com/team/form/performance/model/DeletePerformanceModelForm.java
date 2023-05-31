package com.team.form.performance.model;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 删除专责绩效对象信息
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="删除专责绩效对象信息",description="删除专责绩效对象信息")
public class DeletePerformanceModelForm extends BaseRequest {

    @ApiModelProperty("绩效模板id")
    private Integer id;

    /**
     *  修改人名称
     */
    @ApiModelProperty("用户名称")
    private String updatedBy;

    @Override
    public boolean verifyParam() {
        Assert.notNull(id, "绩效模板id不能为空");
        Assert.notEmpty(updatedBy, "用户名称不能为空");
        return true;
    }
}
