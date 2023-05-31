package com.team.form.performance.monitorModel;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 班长绩效评价模板标准查看对象信息
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="班长绩效评价模板标准查看对象信息",description="班长绩效评价模板标准查看对象信息")
public class QueryMonitorPerforModelRuleListForm extends BaseRequest {

    /**
     *  生产者id
     */
    @ApiModelProperty(value = "用户编码", name = "productorId")
    private String productorId;

    @Override
    public boolean verifyParam() {
        Assert.notEmpty(productorId, "用户编码不能为空");
        return true;
    }
}
