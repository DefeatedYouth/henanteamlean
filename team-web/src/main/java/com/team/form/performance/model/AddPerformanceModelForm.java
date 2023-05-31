package com.team.form.performance.model;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="新增专责绩效对象信息",description="新增专责绩效对象信息")
public class AddPerformanceModelForm extends BaseRequest {

    /**
     * 指标名称
     */
    @ApiModelProperty(value = "指标名称", name = "quateName")
    private String quateName;

    /**
     * 细则
     */
    @ApiModelProperty(value = "细则", name = "detailRule")
    private String detailRule;

    /**
     * 考核周期;1：月，2：季度
     */
    @ApiModelProperty(value = "考核周期;1：月，2：季度", name = "assessmentCycle")
    private Integer assessmentCycle;

    /**
     * 考核人;(角色编码, name = "id")
     */
    @ApiModelProperty(value = "考核人", name = "assessor")
    private String assessor;

    /**
     * 考核对象;(角色编码, name = "id")
     */
    @ApiModelProperty(value = "考核对象", name = "assessmentObject")
    private String assessmentObject;

    /**
     * 积分
     */
    @ApiModelProperty(value = "积分", name = "integral")
    private BigDecimal integral;

    /**
     * 生产者id
     */
    @ApiModelProperty(value = "用户编码", name = "productorId")
    private String productorId;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "用户名称", name = "createdBy")
    private String createdBy;

    @Override
    public boolean verifyParam() {
        Assert.notEmpty(productorId, "用户编码不能为空");
        Assert.notEmpty(createdBy, "用户名称不能为空");
        Assert.notEmpty(quateName, "指标名称不能为空");
        Assert.notEmpty(detailRule, "细则不能为空");
        Assert.notNull(assessmentCycle, "考核周期不能为空");
        return true;
    }
}
