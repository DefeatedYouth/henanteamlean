package com.team.form.work;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工作日历总计")
public class WorkCalendarForm extends BaseRequest {
    @ApiModelProperty("所属月份yyyyMM")
    private String month;

    @ApiModelProperty("所属班组")
    private Integer teamId;

    @ApiModelProperty("所属人员")
    private Integer memberId;

    @Override
    public boolean verifyParam() {
        Assert.notEmpty(month, "时间不能为空");
        return true;
    }
}
