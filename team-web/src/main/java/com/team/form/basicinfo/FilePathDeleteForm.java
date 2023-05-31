package com.team.form.basicinfo;

import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FilePathDeleteForm extends BaseRequest {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "文件路径")
    private String path;

    @Override
    public boolean verifyParam() {
        return true;
    }
}
