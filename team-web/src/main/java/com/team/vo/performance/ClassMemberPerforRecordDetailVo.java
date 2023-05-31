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
@ApiModel(value="班员绩效评价记录对象信息",description="班员绩效评价记录对象信息")
public class ClassMemberPerforRecordDetailVo {

    /**
     * 流水号
     */
    @ApiModelProperty(value = "流水号", name = "id")
    private Integer detailId;

    /**
     * 指标名称
     */
    @ApiModelProperty(value = "指标名称", name = "quateName")
    private String quateName;

    /**
     * 说明描述
     */
    @ApiModelProperty(value = "说明描述", name = "remark")
    private String remark;

    /**
     * 积分
     */
    @ApiModelProperty(value = "积分", name = "integral")
    private BigDecimal integral;

}
