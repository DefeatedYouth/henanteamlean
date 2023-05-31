package com.team.vo.performance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="班员绩效评价记录对象信息",description="班员绩效评价记录对象信息")
public class ClassMemberPerforRecordVo {

    /**
     * 流水号
     */
    @ApiModelProperty(value = "流水号", name = "id")
    private Integer id;

    /**
     * 被考核班员编码
     */
    @ApiModelProperty(value = "被考核班员编码", name = "teamPeoCode")
    private String teamPeoCode;

    /**
     * 被考核班员名称
     */
    @ApiModelProperty(value = "被考核班员名称", name = "teamPeoName")
    private String teamPeoName;

    /**
     * 最终积分
     */
    @ApiModelProperty(value = "最终积分", name = "lastIntegral")
    private BigDecimal lastIntegral;

    /**
     * 绩效等级
     */
    @ApiModelProperty(value = "绩效等级", name = "perforLevel")
    private String perforLevel;

    /**
     * 状态;1:未发布（默认）;2:预发布；3:已发布
     */
    @ApiModelProperty(value = "状态;1:未发布（默认）;2:预发布；3:已发布", name = "status")
    private Integer status;

    /**
     * 班员评价详情
     */
    @ApiModelProperty(value = "班员评价详情列表", name = "detailList")
    private List<ClassMemberPerforRecordDetailVo> detailList;

}
