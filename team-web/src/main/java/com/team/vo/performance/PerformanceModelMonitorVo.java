package com.team.vo.performance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="班长绩效对象信息",description="班长绩效对象信息")
public class PerformanceModelMonitorVo {

    @ApiModelProperty(value = "流水号", name = "id")
    private Integer id;

    /**
     * 班组名称
     */
    @ApiModelProperty(value = "班组名称", name = "teamName")
    private String teamName;

    /**
     * 是否上级派发;1：是，2：否
     */
    @ApiModelProperty(value = "是否上级派发;1：是，2：否", name = "resource")
    private Integer source;

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
     * 发布状态：1未发布，2发布中,3发布
     */
    @ApiModelProperty(value = "发布状态：1未发布，2发布中,3发布", name = "publishState")
    private Integer publishState;

}
