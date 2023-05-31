package com.team.vo.performance;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="专责绩效对象信息",description="专责绩效对象信息")
public class PerformanceModelVo {

    @ApiModelProperty(value = "流水号", name = "id")
    private Integer id;

    /**
     * 是否含有班组：1不包含，2包含（见表：T_PERFORMANCE_MODEL_TEAMREL）
     */
    @ApiModelProperty(value = "是否含有班组：1不包含，2包含", name = "isHavTeam")
    private Integer isHavTeam;

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
     * 编制单位
     */
    @ApiModelProperty(value = "编制单位", name = "company")
    private String companyName;

    /**
     * 编制人
     */
    @ApiModelProperty(value = "编制人", name = "createdBy")
    private String createdBy;

    /**
     * 发布时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "发布时间", name = "publishTime")
    private Date publishTime;

    /**
     * 发布状态：1未发布，2发布中,3发布
     */
    @ApiModelProperty(value = "发布状态：1未发布，2发布中,3发布", name = "publishState")
    private Integer publishState;

    /**
     * 班组名称
     */
    @ApiModelProperty(value = "班组名称", name = "teamName")
    private String teamName;
}
