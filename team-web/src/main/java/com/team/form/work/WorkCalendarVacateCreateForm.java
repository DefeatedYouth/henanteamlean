package com.team.form.work;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单日志假期创建")
public class WorkCalendarVacateCreateForm extends BaseRequest {
    @ApiModelProperty("员工ID")
    private String memberId;
    @ApiModelProperty("员工名称")
    private String memberName;
    @ApiModelProperty("请假类型(0旷工、1事假、2病假、3婚假、4丧假、5产假、6探亲假、7年休假、8护理假、9疗休养)")
    private Integer leaveType;
    @ApiModelProperty("开始时间yyyyMMdd")
    private String startTime;
    @ApiModelProperty("结束时间yyyyMMdd")
    private String endTime;
    @ApiModelProperty("备注")
    private String remark;

    @Override
    public boolean verifyParam() {
        Assert.notNull(memberId,"员工ID不能为空");
        Assert.notNull(startTime,"开始时间不能为空");
        Assert.notNull(endTime,"结束时间不能为空");
        Assert.notNull(leaveType,"请假类型不能为空");
        return true;
    }
}
