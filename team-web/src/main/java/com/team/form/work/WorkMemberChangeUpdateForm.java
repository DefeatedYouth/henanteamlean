package com.team.form.work;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 工单人员查询
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单人员修改--人员变动页面")
public class WorkMemberChangeUpdateForm extends BaseRequest {
    @ApiModelProperty("工单No")
    private String workNo;
    @ApiModelProperty("人员表ID")
    private Integer id;
    @ApiModelProperty("成员ID")
    private String memberId;
    @ApiModelProperty("成员名称")
    private String memberName;
    @ApiModelProperty("出勤日期json,删除传空 [ \"20220721\", \"20220722\"]")
    private String workDays;

    @Override
    public boolean verifyParam() {
        Assert.notEmpty(workNo, "工单编码不能为空");
        Assert.notEmpty(memberId, "人员ID不能为空");
        return true;
    }
}
