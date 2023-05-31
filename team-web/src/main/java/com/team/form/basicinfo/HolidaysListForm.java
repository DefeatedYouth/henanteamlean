package com.team.form.basicinfo;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value="",description="列表展示")
public class HolidaysListForm extends BaseRequest {

    @ApiModelProperty("年份")
    private String year;


    @Override
    public boolean verifyParam() {
        Assert.notNull(year,"年份不能为空");
        return true;
    }
}
