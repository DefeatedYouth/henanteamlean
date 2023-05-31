package com.team.form;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author QianT
 * @date 2022/7/27$
 */
@Data
@ApiModel(value = "新增")
public class TestForm extends BaseRequest {

    @ApiModelProperty(value = "ID", name = "id")
    private String id ;

    @Override
    public boolean verifyParam() {
        Assert.notEmpty(id, "查询id为空");
        return true;
    }
}
