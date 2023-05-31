package com.team.form.basicinfo;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value = "" ,description = "修改")
public class HolidaysUpdateForm extends BaseRequest {

    @ApiModelProperty("节假日id")
    private Integer holidaysId;
    @ApiModelProperty("节假日名称")
    private String holidaysName;
    @ApiModelProperty("节假日开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;
    @ApiModelProperty("节假日结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;
    @ApiModelProperty("调休时间")
    private List<String> rest;


    @Override
    public boolean verifyParam() {
        Assert.notNull(holidaysId,"节假日id不能为空");
        Assert.notEmpty(holidaysName,"节假日名称不能为空");
        Assert.notNull(startTime, "节假日开始时间不能为空");
        Assert.notNull(endTime, "节假日结束时间不能为空");
        Assert.notNull(rest,"调休不能为空");
        return true;
    }
}
