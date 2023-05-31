package com.team.vo.performance;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="历史绩效发布对象信息",description="历史绩效发布对象信息")
public class HistoryPerforModelVo {

    @ApiModelProperty(value = "流水号", name = "id")
    private Integer id;

    /**
     * 细则
     */
    @ApiModelProperty(value = "细则", name = "detailRule")
    private String detailRule;

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
     * 考核周期;1：月，2：季度
     */
    @ApiModelProperty(value = "考核周期;1：月，2：季度", name = "assessmentCycle")
    private Integer assessmentCycle;
}
