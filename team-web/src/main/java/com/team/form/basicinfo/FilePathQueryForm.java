package com.team.form.basicinfo;

import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FilePathQueryForm extends BaseRequest {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @Override
    public boolean verifyParam() {
        return true;
    }
}
