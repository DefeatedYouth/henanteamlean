package com.team.vo.work;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单人员信息--人员变动页面")
public class WorkMemberChangeQueryVo {
    @ApiModelProperty("工单SN")
    private String workNo;

    @ApiModelProperty("人员表ID")
    private Integer id;

    @ApiModelProperty("成员ID")
    private String memberId;

    @ApiModelProperty("成员名称")
    private String memberName;

    @ApiModelProperty("是否负责人(0不是、1是)")
    private Integer leaderFlag;

    @ApiModelProperty("出勤日期json [ \"20220721\", \"20220722\"]")
    private String workDays;
}
