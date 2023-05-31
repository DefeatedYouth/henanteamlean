package com.team.vo.work;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单分页列表人员信息")
public class WorkListPageMemberVo {

    @ApiModelProperty("成员ID")
    private String memberId;

    @ApiModelProperty("成员名称")
    private String memberName;

    @ApiModelProperty("累计积分")
    private String totalIntegral;

    @ApiModelProperty("最终积分")
    private String finalIntegral;
}
