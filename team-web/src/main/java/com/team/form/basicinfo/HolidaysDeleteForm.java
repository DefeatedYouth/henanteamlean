package com.team.form.basicinfo;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value="",description="删除")
public class HolidaysDeleteForm extends BaseRequest {

    @ApiModelProperty("节假日id")
    private List<Integer> holidaysIds;

    @Override
    public boolean verifyParam() {
        Assert.notNull(holidaysIds,"节假日id不能为空");
        return true;
    }
}
