package com.team.vo.work;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单人员信息")
public class WorkMemberQueryVo {

    @ApiModelProperty("工单ID")
    private Integer workSn;

    @ApiModelProperty("人员表ID")
    private Integer id;

    @ApiModelProperty("成员ID")
    private String memberId;

    @ApiModelProperty("成员名称")
    private String memberName;

    @ApiModelProperty("出勤日期json [ \"2022-07-21\", \"2022-07-22\"]")
    private String workDays;

    @ApiModelProperty("累计积分")
    private String totalIntegral;

    @ApiModelProperty("最终积分")
    private String finalIntegral;
}
