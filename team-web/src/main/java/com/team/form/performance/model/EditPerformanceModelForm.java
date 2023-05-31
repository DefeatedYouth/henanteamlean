package com.team.form.performance.model;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
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
@ApiModel(value="编辑专责绩效对象信息",description="编辑专责绩效对象信息")
public class EditPerformanceModelForm extends BaseRequest {

    /**
     * 流水号
     */
    @ApiModelProperty(value = "流水号", name = "id")
    private Integer id;

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
     *  修改人名称
     */
    @ApiModelProperty(value = "用户名称", name = "updatedBy")
    private String updatedBy;

    @Override
    public boolean verifyParam() {
        Assert.notNull(id, "流水号id为空,编辑失败");
        Assert.notEmpty(updatedBy, "用户名称不能为空");
        Assert.notEmpty(quateName, "指标名称不能为空");
        Assert.notEmpty(detailRule, "细则不能为空");
        Assert.notNull(assessmentCycle, "考核周期不能为空");
        return true;
    }
}
