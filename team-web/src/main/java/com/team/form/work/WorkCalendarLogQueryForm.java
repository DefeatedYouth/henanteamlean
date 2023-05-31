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
@ApiModel(value="",description="工单日志查询")
public class WorkCalendarLogQueryForm extends BaseRequest {

    @ApiModelProperty("开始时间yyyyMMdd")
    private String startTime;
    @ApiModelProperty("结束时间yyyyMMdd")
    private String endTime;


    @Override
    public boolean verifyParam() {
        Assert.notNull(startTime,"开始时间不能为空");
        Assert.notNull(endTime,"结束时间不能为空");
        return true;
    }
}
